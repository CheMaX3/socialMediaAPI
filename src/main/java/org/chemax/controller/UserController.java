package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @PostMapping(value = "/user_create")
    ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest);

    @GetMapping(value = "/user_get")
    ResponseEntity<UserDTO> getUserById(@RequestParam Long userId);

    @DeleteMapping(value = "/user_delete")
    ResponseEntity<Void> deleteUserById(@RequestParam Long userId);

    @PutMapping(value = "/user_update")
    ResponseEntity<Void> updateUserById(@RequestParam Long userId,
                        @RequestBody UserUpdateRequest userUpdateRequest);
}
