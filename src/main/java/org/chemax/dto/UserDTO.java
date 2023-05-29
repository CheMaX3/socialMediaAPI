package org.chemax.dto;

import org.chemax.entity.Friend;
import org.chemax.entity.Subscribed;
import org.chemax.entity.Subscriber;

import java.util.List;

public class UserDTO {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private List<Friend> friendList;
    private List<Subscriber> subscribers;
    private List<Subscribed> subscribed;

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

    public List<Friend> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Subscribed> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<Subscribed> subscribed) {
        this.subscribed = subscribed;
    }
}
