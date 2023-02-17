package finalproject.springproject.services;

import finalproject.springproject.dto.UserDto;
import finalproject.springproject.models.Permission;
import finalproject.springproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean registerUser(UserDto userDto);
    void updatePassword(String oldPassword, String newPassword, String retypePassword);
    User getCurrentUser();
    User saveUserData(User user);
    List<User> getUsers();
    User getUser(Long id);
    List<Permission> getRoles();
    User saveRole(User user);


}
