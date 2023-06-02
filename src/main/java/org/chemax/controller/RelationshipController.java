package org.chemax.controller;

import org.chemax.entity.Friend;
import org.chemax.entity.FriendshipInvite;
import org.chemax.request.DeleteFriendRequest;
import org.chemax.request.FriendshipInviteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/relationship")
public interface RelationshipController {

    @PostMapping(value = "/create_friendship_invite")
    ResponseEntity<FriendshipInvite> createFriendshipInviteAndSubscribe(@RequestBody FriendshipInviteRequest friendshipInviteRequest);

    @GetMapping(value = "/approve")
    ResponseEntity<Friend> approveFriendshipInviteWithInviteId(@RequestParam Long inviteId);

    @GetMapping(value = "/decline")
    ResponseEntity<Void> declineFriendshipInviteWithInviteId(@RequestParam Long inviteId);

    @PostMapping(value = "/delete_friend")
    ResponseEntity<Void> deleteAndUnsubscribeFromUserWithId(@RequestBody DeleteFriendRequest deleteFriendRequest);
}
