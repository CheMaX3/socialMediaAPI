package org.chemax.service;

import org.chemax.dto.UserDTO;
import org.chemax.entity.User;
import org.chemax.repository.UserRepository;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        try {
            User user = userRepository.save(buildUserFromRequest(userCreateRequest));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = convertUserToUserDTO(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.delete(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateUserById(Long userId, UserUpdateRequest userUpdateRequest) {
        try {
            User userFromDB = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            userFromDB.setUsername(Optional.ofNullable(userUpdateRequest.getUsername())
                    .orElse(userFromDB.getUsername()));
            userFromDB.setEmail(Optional.ofNullable(userUpdateRequest.getEmail()).orElse(userFromDB.getEmail()));
            userFromDB.setPassword(Optional.ofNullable(userUpdateRequest.getPassword())
                    .orElse(userFromDB.getPassword()));
            userRepository.save(userFromDB);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private User buildUserFromRequest(UserCreateRequest userCreateRequest) {
        User builtUser = new User();
        builtUser.setUsername(userCreateRequest.getUsername());
        builtUser.setEmail(userCreateRequest.getEmail());
        builtUser.setPassword(userCreateRequest.getPassword());
        return builtUser;
    }

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFriendList(Optional.ofNullable(user.getFriendList()).orElse(new ArrayList<>()));
        userDTO.setSubscribers(Optional.ofNullable(user.getSubscribers()).orElse(new ArrayList<>()));
        userDTO.setSubscribed(Optional.ofNullable(user.getSubscribed()).orElse(new ArrayList<>()));
        return userDTO;
    }
}
