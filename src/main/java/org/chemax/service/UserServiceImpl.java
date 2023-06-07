package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.UserDTO;
import org.chemax.entity.Role;
import org.chemax.entity.User;
import org.chemax.repository.*;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    private final FriendshipInviteRepository friendshipInviteRepository;

    private final FriendRepository friendRepository;

    private final SubscriberRepository subscriberRepository;

    private final SubscribedRepository subscribedRepository;

    private final PostService postService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, FriendshipInviteRepository friendshipInviteRepository,
                           FriendRepository friendRepository, SubscriberRepository subscriberRepository,
                           SubscribedRepository subscribedRepository, PostService postService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
        this.friendRepository = friendRepository;
        this.subscriberRepository = subscriberRepository;
        this.subscribedRepository = subscribedRepository;
        this.postService = postService;
    }

    @Override
    public void createUser(UserCreateRequest userCreateRequest) {
        try {
            User user = buildUserFromRequest(userCreateRequest);
            userRepository.save(user);
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
            log.error("Can't retrieve object from DB with userId=" + userId);
        }
        return userDTO;
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception ex) {
            log.error("Can't delete object with userId=" + userId);
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
            log.error("Can't save object with userId=" + userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
            try {
                allUsers = userRepository.findAll();
            }
            catch (Exception ex) {
                log.error("Can't retrieve objects from DB");
            }
        return allUsers;
    }

    private User buildUserFromRequest(UserCreateRequest userCreateRequest) {
        User builtUser = new User();
        builtUser.setUsername(userCreateRequest.getUsername());
        builtUser.setEmail(userCreateRequest.getEmail());
        builtUser.setPassword(userCreateRequest.getPassword());
        builtUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        builtUser.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));

        return builtUser;
    }

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFriendList(friendRepository.findByRequesterId(user.getUserId()));
        userDTO.setSubscribedList(subscribedRepository.findByRequesterId(user.getUserId()));
        userDTO.setSubscribersList(subscriberRepository.findByRequestedId(user.getUserId()));
        userDTO.setFriendshipInvitesList(friendshipInviteRepository.findFriendshipInvitesByRequestedId(user.getUserId()));
        userDTO.setPosts(postService.getPostsByAuthorId(user.getUserId()));

        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = new User();
        try {
            user = userRepository.findByUsername(username);
        }
        catch (Exception ex) {
            log.error("Can't find user with username=" + username);
        }
        return user;
    }


}
