package finalproject.springproject.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

//    //////////////////////////////////////////
//    @ManyToMany
//    @JoinTable(
//            name = "t_user_permissions",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "permissions_id")
//    )
//    private Set<Permission> permissionSet = new HashSet<>();
//    ///////////////////////////////////////////

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permission> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
