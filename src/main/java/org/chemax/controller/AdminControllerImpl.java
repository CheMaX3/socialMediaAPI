package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.chemax.entity.User;
import org.chemax.request.UserUpdateRequest;
import org.chemax.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    public AdminControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<List<User>> getUserList() {
        final List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteUserById(Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> updateUserById(Long userId, UserUpdateRequest userUpdateRequest) {
        final UserDTO userDTO = userService.updateUserById(userId, userUpdateRequest);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
