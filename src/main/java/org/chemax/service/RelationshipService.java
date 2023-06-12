package org.chemax.service;

import org.chemax.entity.FriendshipInvite;
import org.chemax.request.DeleteFriendRequest;
import org.chemax.request.FriendshipInviteRequest;

public interface RelationshipService {

    FriendshipInvite createFriendshipInviteAndSubscribe(FriendshipInviteRequest friendshipInviteRequest);

    boolean makeUsersFriendsAndSubscribersByFriendshipInviteId(Long inviteId);

    void deleteFriendshipInviteWithId(Long inviteId);

    void deleteAndUnsubscribeFromRequestedUser(DeleteFriendRequest deleteFriendRequest);
}
