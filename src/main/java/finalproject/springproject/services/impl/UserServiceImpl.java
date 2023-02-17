package finalproject.springproject.services.impl;

import finalproject.springproject.dto.UserDto;
import finalproject.springproject.models.Permission;
import finalproject.springproject.models.User;
import finalproject.springproject.repository.PermissionRepository;
import finalproject.springproject.repository.UserRepository;
import finalproject.springproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }


    @Override
    public boolean registerUser(UserDto userDto) {
        if (userDto.getPassword().equals(userDto.getRetypePassword())) {
            User checkUser = userRepository.findByEmail(userDto.getEmail());
                if (checkUser == null) {
                    Permission permission = permissionRepository.findByName("ROLE_USER");
                    User user = new User();
                    user.setEmail(userDto.getEmail());
                    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                    user.setFullName(userDto.getFullName());
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(permission);
                    user.setPermissions(permissions);
                    User newUser = userRepository.save(user);
                    return newUser.getId() != null;
                }
        }

        return false;
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword, String retypePassword) {
        if (newPassword.equals(retypePassword)) {
            User user = getCurrentUser();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }
        }
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    @Override
    public User saveUserData(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Permission> getRoles() {
        return permissionRepository.findAll();
    }

    @Override
    public User saveRole(User user) {
        User checkUser = userRepository.findById(user.getId()).orElseThrow();

        if (checkUser != null) {

            Permission permission = permissionRepository.findByName("ROLE_USER");

            List<Permission> permissions = new ArrayList<>();
            permissions.add(permission);
            checkUser.setPermissions(permissions);

            return userRepository.save(checkUser);
        }

        return null;
    }

}
