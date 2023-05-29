package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.chemax.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;

    public UserControllerImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> createUser(UserCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Long userId) {
        final UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateUserById(Long userId, UserUpdateRequest userUpdateRequest) {
        userService.updateUserById(userId, userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
