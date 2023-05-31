package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.UserDTO;
import org.chemax.entity.FriendshipInvite;
import org.chemax.entity.User;
import org.chemax.repository.FriendshipInviteRepository;
import org.chemax.repository.UserRepository;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final FriendshipInviteRepository friendshipInviteRepository;

    public UserServiceImpl(UserRepository userRepository, FriendshipInviteRepository friendshipInviteRepository) {
        this.userRepository = userRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        try {
            userRepository.save(buildUserFromRequest(userCreateRequest));
        }
        catch (Exception ex) {
            log.error("Can't save object " + userCreateRequest.toString());
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = convertUserToUserDTO(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB with id=" + userId);
        }
        return userDTO;
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception ex) {
            log.error("Can't delete object with id=" + userId);
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
            log.error("Can't save object with id=" + userId);
        }
    }

    @Override
    public List<FriendshipInvite> getFriendshipInviteListByUserId(Long userId) {
        List<FriendshipInvite> friendshipInvites = new ArrayList<>();
        try {
            friendshipInvites = new ArrayList<>(friendshipInviteRepository.findFriendshipInvitesByRequestedId(userId));
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return friendshipInvites;
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
        return userDTO;
    }
}
