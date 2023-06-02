package org.chemax.controller;

import org.chemax.request.UserCreateRequest;
import org.chemax.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationControllerImpl implements RegistrationController {

    private final UserService userService;

    public RegistrationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> createUser(UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
