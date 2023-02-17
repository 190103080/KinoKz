package finalproject.springproject.controllers;

import finalproject.springproject.dto.UserDto;
import finalproject.springproject.models.Permission;
import finalproject.springproject.models.User;
import finalproject.springproject.services.CinemaService;
import finalproject.springproject.services.FileUploadService;
import finalproject.springproject.services.KinoService;
import finalproject.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private UserService userService;

    @Autowired
    private KinoService kinoService;

    @GetMapping(value = "/")
    public String homePage(Model model) {
        model.addAttribute("cinemas", cinemaService.getCinemas());
        model.addAttribute("kinoteatry", kinoService.getKinoteatrys());
        model.addAttribute("sortcinema", cinemaService.getSortCinemas());
        return "home";
    }

    @GetMapping(value = "/enter")
    public String enterPage() {
        return "enter";
    }

    @GetMapping(value = "/403")
    public String deniedPage() {
        return "403";
    }

    @GetMapping(value = "/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUP(UserDto userDto) {
        if (userService.registerUser(userDto)) {
            return "redirect:/signup?success";
        }
        return "redirect:/signup?error";
    }

    @PostMapping(value = "/updatepassword")
    public String updatePassword(@RequestParam(name = "old_password") String oldPassword,
                                 @RequestParam(name = "new_password") String newPassword,
                                 @RequestParam(name = "re_new_password") String reNewPassword) {
        userService.updatePassword(oldPassword, newPassword, reNewPassword);
        return "redirect:/profile";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage() {
        return "profile";
    }

    @GetMapping(value = "/adminpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminPanelPage() {
        return"admin";
    }

    @GetMapping(value = "/editorpanel")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public String editorPage() {
        return "editor";
    }

    @GetMapping(value = "/allUsers")
    public String pageAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "allUsers";
    }

    @GetMapping(value = "/privilege/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR')")
    public String pagePrivilege(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getUser(id);
        List<Permission> permissionList = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("permissionList", permissionList);

        return "privilege";
    }

    @PostMapping(value = "/privilege/{id}/updatePrivilege")
    public String updatePrivilege(User user, @PathVariable(name = "id") Long id){
        User us = userService.getUser(id);
        userService.saveRole(us);

        return "redirect:/allUsers";
    }

//    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/uploadavatar")
    public String uploadAvatar(@RequestParam(name = "avatar_pic") MultipartFile file) {
        fileUploadService.uploadAva(file);
        return "redirect:/";
    }

//    @PreAuthorize("isAuthenticated()")
//    @PostMapping(value = "/viewpic/{picToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
//    public @ResponseBody byte[] viewPic(@PathVariable(name = "picToken") String token) throws IOException{
//        return fileUploadService.getAvatar(token);
//    }



}
