package org.chemax.controller;

import org.chemax.dto.UserDTO;
import org.chemax.entity.FriendshipInvite;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/user")
public interface UserController {
    @PostMapping(value = "/create")
    ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest);

    @GetMapping(value = "/get")
    ResponseEntity<UserDTO> getUserById(@RequestParam Long userId);

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deleteUserById(@RequestParam Long userId);

    @PutMapping(value = "/update")
    ResponseEntity<Void> updateUserById(@RequestParam Long userId,
                                        @RequestBody UserUpdateRequest userUpdateRequest);

    @GetMapping(value = "/getAllFriendshipInvites")
    ResponseEntity<List<FriendshipInvite>> getFriendshipInviteListByUserId(@RequestParam Long userId);
}
