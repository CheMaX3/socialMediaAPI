package org.chemax.config;

import org.chemax.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasAnyRole("ADMIN")
                    .antMatchers("/user/**", "/media/**", "/post/**", "/relationship/**")
                    .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/login/**", "/registration/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .httpBasic()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}