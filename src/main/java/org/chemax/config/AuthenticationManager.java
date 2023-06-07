//package org.chemax.config;
//
//import io.jsonwebtoken.Claims;
//import org.apache.log4j.Logger;
//import org.chemax.entity.Role;
//import org.chemax.repository.UserRepository;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//
//@Component
//public class AuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {
//
//    private static final Logger log = Logger.getLogger(AuthenticationManager.class.getName());
//
//    private final JwtUtil jwtUtil;
//
//    private final UserRepository userRepository;
//
//    public AuthenticationManager(JwtUtil jwtUtil, UserRepository userRepository) {
//        this.jwtUtil = jwtUtil;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String authToken = authentication.getPrincipal().toString();
//        String username;
//        try {
//                username = jwtUtil.extractUsername(authToken);
//            }
//            catch (Exception ex) {
//                username = null;
//                log.error("Invalid token");
//            }
//        if (username != null && jwtUtil.validateToken(authToken)) {
//            Claims claims = jwtUtil.getClaimsFromToken(authToken);
//            Set<Role> authorities = userRepository.findByUsername(username).getRoles();
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(username, null, authorities);
//            return authenticationToken;
//        } else {
//            return authentication;
//        }
//    }
//}
