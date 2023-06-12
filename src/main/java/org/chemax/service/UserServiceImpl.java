package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.dto.UserDTO;
import org.chemax.entity.Role;
import org.chemax.entity.User;
import org.chemax.exception.FieldCantBeEmptyException;
import org.chemax.repository.UserRepository;
import org.chemax.request.UserCreateRequest;
import org.chemax.request.UserUpdateRequest;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;

    private final FriendshipInviteService friendshipInviteService;

    private final FriendService friendService;

    private final SubscriberService subscriberService;

    private final SubscribedService subscribedService;

    private final PostService postService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, FriendshipInviteService friendshipInviteService,
                           FriendService friendService, SubscriberService subscriberService,
                           SubscribedService subscribedService, PostService postService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.friendshipInviteService = friendshipInviteService;
        this.friendService = friendService;
        this.subscriberService = subscriberService;
        this.subscribedService = subscribedService;
        this.postService = postService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserCreateRequest userCreateRequest) {
        try {
            User user = buildUserFromRequest(userCreateRequest);
            userRepository.save(user);
            return convertUserToUserDTO(user);
        }
        catch (Exception ex) {
            log.error("Can't save object " + userCreateRequest.toString());
            throw new DataSourceLookupFailureException("Can't save object");
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        try {
            User userFromDB = getUserEntityById(userId);
            return convertUserToUserDTO(userFromDB);
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        try {
            userRepository.deleteById(userId);
        }
        catch (Exception ex) {
            log.error("User with userId=" + userId + " not exists");
            throw new EntityNotFoundException("User with userId=" + userId + " not exists");
        }
    }

    @Override
    public UserDTO updateUserById(Long userId, UserUpdateRequest userUpdateRequest) {
        try {
            User userFromDB = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
            userFromDB.setUsername(Optional.ofNullable(userUpdateRequest.getUsername())
                    .orElse(userFromDB.getUsername()));
            userFromDB.setEmail(Optional.ofNullable(userUpdateRequest.getEmail()).orElse(userFromDB.getEmail()));
            userFromDB.setPassword(Optional.ofNullable(passwordEncoder.encode(userUpdateRequest.getPassword()))
                    .orElse(userFromDB.getPassword()));
            userRepository.save(userFromDB);

            return convertUserToUserDTO(userFromDB);
        } catch (Exception ex) {
            log.error("Can't find user with userId=" + userId);
            throw new EntityNotFoundException("Can't find user with userId=" + userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username);
        }
        catch (Exception ex) {
            log.error("Can't find user with username=" + username);
            throw new EntityNotFoundException("Can't find user with username=" + username);
        }
    }

    private User getUserEntityById(Long userId) {
        try {
            return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        }
        catch (Exception ex) {
            log.error("Can't find user with userId=" + userId);
            throw new EntityNotFoundException("Can't find user with userId=" + userId);
        }
    }

    private User buildUserFromRequest(UserCreateRequest userCreateRequest) {
        try {
            User builtUser = new User();
            builtUser.setUsername(Optional.ofNullable(userCreateRequest.getUsername())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtUser.setEmail(Optional.ofNullable(userCreateRequest.getEmail())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
            builtUser.setPassword(Optional.ofNullable(passwordEncoder.encode(userCreateRequest.getPassword()))
                    .orElseThrow(FieldCantBeEmptyException::new));

            return builtUser;
        }
        catch (Exception ex) {
            log.error("Bad request " + userCreateRequest.toString());
            throw new FieldCantBeEmptyException();
        }
    }

    private UserDTO convertUserToUserDTO(User user) {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(Optional.ofNullable(user.getUserId()).orElseThrow(FieldCantBeEmptyException::new));
            userDTO.setUsername(Optional.ofNullable(user.getUsername()).orElseThrow(FieldCantBeEmptyException::new));
            userDTO.setFriendList(friendService.getFriendListByRequesterId(user.getUserId()));
            userDTO.setSubscribedList(subscribedService.getSubscribedListByRequesterId(user.getUserId()));
            userDTO.setSubscribersList(subscriberService.getSubscriberListByRequestedId(user.getUserId()));
            userDTO.setFriendshipInvitesList(friendshipInviteService.getFriendshipInvitesListByRequestedId(user.getUserId()));
            userDTO.setPosts(postService.getPostsByAuthorId(user.getUserId()));

            return userDTO;
        } catch (Exception ex) {
            log.error("Wrong user" + user.getUserId());
            throw new FieldCantBeEmptyException();
        }
    }
}
