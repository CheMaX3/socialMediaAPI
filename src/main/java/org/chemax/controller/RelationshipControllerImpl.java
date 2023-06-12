package org.chemax.controller;

import org.chemax.entity.FriendshipInvite;
import org.chemax.request.DeleteFriendRequest;
import org.chemax.request.FriendshipInviteRequest;
import org.chemax.service.RelationshipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationshipControllerImpl implements RelationshipController {

    private final RelationshipService relationshipService;

    public RelationshipControllerImpl(RelationshipService relationshipService) {
        this.relationshipService = relationshipService;
    }

    @Override
    public ResponseEntity<FriendshipInvite> createFriendshipInviteAndSubscribe(FriendshipInviteRequest friendshipInviteRequest) {
        final FriendshipInvite invite = relationshipService.createFriendshipInviteAndSubscribe(friendshipInviteRequest);
        return new ResponseEntity<>(invite, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> approveFriendshipInviteWithInviteId(Long inviteId) {
        final Boolean approved = relationshipService.makeUsersFriendsAndSubscribersByFriendshipInviteId(inviteId);
        return new ResponseEntity<>(approved, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> declineFriendshipInviteWithInviteId(Long inviteId) {
        relationshipService.deleteFriendshipInviteWithId(inviteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAndUnsubscribeFromUserWithId(DeleteFriendRequest deleteFriendRequest) {
        relationshipService.deleteAndUnsubscribeFromRequestedUser(deleteFriendRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
