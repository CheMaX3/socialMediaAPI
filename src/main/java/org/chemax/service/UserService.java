package org.chemax.service;

import org.chemax.dto.UserDTO;
import org.chemax.entity.User;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserCreateRequest userCreateRequest);

    UserDTO getUserById(Long userId);

    void deleteUserById(Long userId);

    UserDTO updateUserById(Long userId, UserUpdateRequest userUpdateRequest);

    List<User> getAllUsers();
}
