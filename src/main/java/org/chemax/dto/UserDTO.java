package org.chemax.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.chemax.entity.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Schema(description = "DTO сущности пользователя")
public class UserDTO  {

    @NotEmpty
    @Schema(description = "Id пользователя")
    private Long userId;

    @NotEmpty
    @Schema(description = "Имя пользователя/логин")
    private String username;

    @Schema(description = "Лист друзей пользователя")
    private List<Friend> friendList;

    @Schema(description = "Лист пользователей, на которых подписан пользователь")
    private List<Subscriber> subscribersList;

    @Schema(description = "Лист пользователей, которые подписаны на пользователя")
    private List<Subscribed> subscribedList;

    @Schema(description = "Лист приглашений в друзья пользователя")
    private List<FriendshipInvite> friendshipInvitesList;

    @Schema(description = "Лист постов, автором которых является пользователь")
    private List<PostDTO> posts;

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

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
