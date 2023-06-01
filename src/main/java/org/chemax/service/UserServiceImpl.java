package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.UserDTO;
import org.chemax.entity.Role;
import org.chemax.entity.User;
import org.chemax.repository.*;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
    private final UserRepository userRepository;
    private final FriendshipInviteRepository friendshipInviteRepository;
    private final FriendRepository friendRepository;
    private final SubscriberRepository subscriberRepository;
    private final SubscribedRepository subscribedRepository;
    private final PostRepository postRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, FriendshipInviteRepository friendshipInviteRepository,
                           FriendRepository friendRepository, SubscriberRepository subscriberRepository,
                           SubscribedRepository subscribedRepository, PostRepository postRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
        this.friendRepository = friendRepository;
        this.subscriberRepository = subscriberRepository;
        this.subscribedRepository = subscribedRepository;
        this.postRepository = postRepository;
        this.roleRepository = roleRepository;
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
        builtUser.setPassword(bCryptPasswordEncoder.encode(userCreateRequest.getPassword()));
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
        userDTO.setPosts(postRepository.findByAuthorId(user.getUserId()));
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

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
