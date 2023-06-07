package org.chemax.config;

import org.chemax.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

//    private final AuthenticationManager authenticationManager;

    private final JwtFilter jwtFilter;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtFilter jwtFilter, UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.jwtFilter = jwtFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


//    public SecurityConfig(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasAnyRole("ADMIN")
                    .antMatchers("/user/**", "/media/**", "/post/**", "/relationship/**")
                    .hasAnyRole("ROLE_USER", "ADMIN")
                    .antMatchers("/login/**", "/registration/**", "/authenticate", "/api/auth/token")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .httpBasic()
                .and()
//                    .authenticationManager(authenticationManager)
                    .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}