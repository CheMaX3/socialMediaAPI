package org.chemax.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String passwordConfirm;

    @Transient
    private List<Friend> friendList;

    @Transient
    private List<Subscriber> subscribersList;

    @Transient
    private List<Subscribed> subscribedList;

    @Transient
    private List<FriendshipInvite> friendshipInvitesList;

    @Transient
    private List<Post> posts;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public List<Subscriber> getSubscribersList() {
        return subscribersList;
    }

    public void setSubscribersList(List<Subscriber> subscribersList) {
        this.subscribersList = subscribersList;
    }

    public List<Subscribed> getSubscribedList() {
        return subscribedList;
    }

    public void setSubscribedList(List<Subscribed> subscribedList) {
        this.subscribedList = subscribedList;
    }

    public List<FriendshipInvite> getFriendshipInvitesList() {
        return friendshipInvitesList;
    }

    public void setFriendshipInvitesList(List<FriendshipInvite> friendshipInvitesList) {
        this.friendshipInvitesList = friendshipInvitesList;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
