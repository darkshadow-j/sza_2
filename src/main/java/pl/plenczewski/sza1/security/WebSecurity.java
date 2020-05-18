package pl.plenczewski.sza1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.plenczewski.sza1.services.LoginCounter;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {


    @Autowired
    private LoginCounter loginCounter;

    @Bean
    public PasswordEncoder getPasswordencoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User userAdmin = new User("admin", getPasswordencoder().encode("admin123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        User userUser = new User("user", getPasswordencoder().encode("user123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/anonymous").permitAll()
                .and().formLogin()
                .permitAll()
                .and().logout().logoutSuccessUrl("/bye");
    }

}
