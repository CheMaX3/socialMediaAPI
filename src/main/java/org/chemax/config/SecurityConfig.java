package org.chemax.config;

import org.chemax.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
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
                    .antMatchers("/login/**", "/registration/**", "/swagger-ui/**", "/v3/**")
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