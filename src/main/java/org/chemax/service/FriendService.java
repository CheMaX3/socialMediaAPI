package org.chemax.service;

import org.chemax.entity.Friend;

import java.util.List;

public interface FriendService {

    List<Friend> getFriendListByRequesterId(Long requesterId);

    boolean friendshipCheck(Long senderId, Long receiverId);
}
