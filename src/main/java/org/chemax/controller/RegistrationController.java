package org.chemax.controller;

import org.chemax.request.UserCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/registration/user")
public interface RegistrationController {

    @PostMapping(value = "/create")
    ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest);
}
