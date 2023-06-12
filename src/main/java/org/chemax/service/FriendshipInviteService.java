package org.chemax.service;

import org.chemax.entity.FriendshipInvite;

import java.util.List;

public interface FriendshipInviteService {

    List<FriendshipInvite> getFriendshipInvitesListByRequestedId(Long requestedId);
}
