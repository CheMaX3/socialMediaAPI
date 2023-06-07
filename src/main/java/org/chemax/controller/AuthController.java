package org.chemax.controller;

import org.chemax.entity.AuthResponse;
import org.chemax.request.AuthRequest;
import org.chemax.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/authenticate")
    ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws AuthException {
        final AuthResponse jwtToken = authService.login(authRequest);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
}
