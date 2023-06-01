package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(value = "/user")
public interface UserController {

    @GetMapping(value = "/get")
    ResponseEntity<UserDTO> getUserById(@RequestParam Long userId);
}
