package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAuthority('ROLE_USER')")
@RequestMapping(value = "/user")
public interface UserController {

    @GetMapping(value = "/get")
    ResponseEntity<UserDTO> getUserById(@RequestParam Long userId);
}
