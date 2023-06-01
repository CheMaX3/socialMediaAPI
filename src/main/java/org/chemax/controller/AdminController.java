package org.chemax.controller;

import org.chemax.entity.User;
import org.chemax.request.UserUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/user")
public interface AdminController {

    @GetMapping("/list")
    ResponseEntity<List<User>> getUserList();

    @DeleteMapping(value = "/delete")
    ResponseEntity<Void> deleteUserById(@RequestParam Long userId);

    @PutMapping(value = "/update")
    ResponseEntity<Void> updateUserById(@RequestParam Long userId,
                                        @RequestBody UserUpdateRequest userUpdateRequest);


}
