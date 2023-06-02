package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.UserDTO;
import org.chemax.entity.Role;
import org.chemax.entity.User;
import org.chemax.repository.*;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final FriendshipInviteRepository friendshipInviteRepository;
    private final FriendRepository friendRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscribedRepository subscribedRepository;
    private final RoleRepository roleRepository;
    private final PostService postService;

    public UserServiceImpl(UserRepository userRepository, FriendshipInviteRepository friendshipInviteRepository,
                           FriendRepository friendRepository, SubscriberRepository subscriberRepository,
                           SubscribedRepository subscribedRepository, RoleRepository roleRepository,
                           PostService postService) {
        this.userRepository = userRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
        this.friendRepository = friendRepository;
        this.subscriberRepository = subscriberRepository;
        this.subscribedRepository = subscribedRepository;
        this.roleRepository = roleRepository;
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

    @Override
    public List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        try {
            allRoles = roleRepository.findAll();
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
        }
        return allRoles;
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
            log.error("User with name: " + username + " not found");
        }
        return user;
    }

    private boolean existUserCheck(String username) {
        boolean userExists = true;
        try {
            User userFromDB = userRepository.findByUsername(username);
            if (userFromDB == null) {
                userExists = false;
            }
        }
        catch (Exception ex) {
           log.error("Can't connect to DB");
        }
        return userExists;
    }
}
