package org.chemax.service;

import org.chemax.entity.Friend;
import org.chemax.entity.FriendshipInvite;
import org.chemax.request.FriendshipInviteRequest;
import org.chemax.request.UnsubscribeRequest;

public interface RelationshipService {
    FriendshipInvite createFriendshipInviteAndSubscribe(FriendshipInviteRequest friendshipInviteRequest);

    Friend makeUsersFriendsAndSubcribersByFriendshipInviteId(Long inviteId);

    void deleteFrindshipInviteWithId(Long inviteId);

    void unsubscribeFromRequestedUser(UnsubscribeRequest unsubscribeRequest);
}
