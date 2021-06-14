package com.example.demo.Config;


import com.example.demo.Model.User;
import com.example.demo.UserDetailsServiceImpl;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepo userRepo;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepo userRepo) {
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api").hasRole("ADMIN")

                .and()
                .formLogin().permitAll()
                .and()
                .logout()
                .and()
                .csrf().disable();



    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
    User user= new User(1L,"User",passwordEncoder().encode("User"),"ROLE_USER");
    User admin= new User(2L,"Admin",passwordEncoder().encode("Admin"),"ROLE_ADMIN");

    userRepo.save(user);
    userRepo.save(admin);

    }


}