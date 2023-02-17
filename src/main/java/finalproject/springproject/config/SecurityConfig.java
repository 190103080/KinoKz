package finalproject.springproject.config;

import finalproject.springproject.services.UserService;
import finalproject.springproject.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder builder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        builder.userDetailsService(userService());

        httpSecurity.exceptionHandling().accessDeniedPage("/403");
        httpSecurity.authorizeHttpRequests().antMatchers("/css/**", "/js/**").permitAll();

        httpSecurity.formLogin()
                .loginProcessingUrl("/auth").permitAll()
                .defaultSuccessUrl("/profile")
                .failureUrl("/enter?error")
                .usernameParameter("user_email")
                .passwordParameter("user_password")
                .loginPage("/enter").permitAll();

        httpSecurity.logout()
                .logoutSuccessUrl("/enter")
                .logoutUrl("/exit");

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

}
