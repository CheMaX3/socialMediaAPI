package org.chemax.service;

import org.chemax.dto.UserDTO;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;

public interface UserService {
    void createUser(UserCreateRequest userCreateRequest);

    UserDTO getUserById(Long userId);

    void deleteUserById(Long userId);

    void updateUserById(Long userId, UserUpdateRequest userUpdateRequest);

}
