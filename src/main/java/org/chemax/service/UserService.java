package org.chemax.service;

import org.chemax.dto.UserDTO;
import org.chemax.entity.Role;
import org.chemax.entity.User;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void createUser(UserCreateRequest userCreateRequest);

    UserDTO getUserById(Long userId);

    void deleteUserById(Long userId);

    void updateUserById(Long userId, UserUpdateRequest userUpdateRequest);

    List<User> getAllUsers();

    List<Role> getAllRoles();
}
