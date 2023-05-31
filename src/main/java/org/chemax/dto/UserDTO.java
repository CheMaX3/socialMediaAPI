package org.chemax.dto;

import org.chemax.entity.*;

import java.util.List;

public class UserDTO {

    private Long userId;
    private String username;
    private List<Friend> friendList;
    private List<Subscriber> subscribersList;
    private List<Subscribed> subscribedList;
    private List<FriendshipInvite> friendshipInvitesList;
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
