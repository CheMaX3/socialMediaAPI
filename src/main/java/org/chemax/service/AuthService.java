package org.chemax.service;

import io.jsonwebtoken.Claims;
import org.apache.log4j.Logger;
import org.chemax.config.JwtAuthentication;
import org.chemax.config.JwtProvider;
import org.chemax.config.PasswordEncoder;
import org.chemax.entity.AuthResponse;
import org.chemax.entity.User;
import org.chemax.repository.UserRepository;
import org.chemax.request.AuthRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private static final Logger log = Logger.getLogger(AuthService.class.getName());
//    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
//
    private final PasswordEncoder passwordEncoder;
//
//
//    public AuthService(JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.jwtUtil = jwtUtil;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public AuthResponse login(AuthRequest authRequest) throws AuthException {
//        AuthResponse authResponse = new AuthResponse("noToken");
//        try {
//            User user = userRepository.findByUsername(authRequest.getUsername());
//            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
//                String jwtToken = jwtUtil.generateToken(user);
//                authResponse.setJwtToken(jwtToken);
//            }
//        }
//        catch (Exception ex) {
//            log.error("Empty authRequest " + authRequest.toString());
//        }
//        return authResponse;
//    }
    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse login(@NotNull AuthRequest authRequest) throws AuthException {
        final User user = userRepository.findByUsername(authRequest.getUsername());
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getUsername(), refreshToken);
            return new AuthResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public AuthResponse getAccessToken(@NotNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(login);
                final String accessToken = jwtProvider.generateAccessToken(user);
                return new AuthResponse(accessToken, null);
            }
        }
        return new AuthResponse(null, null);
    }

    public AuthResponse refresh(@NotNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findByUsername(login);
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getUsername(), newRefreshToken);
                return new AuthResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
