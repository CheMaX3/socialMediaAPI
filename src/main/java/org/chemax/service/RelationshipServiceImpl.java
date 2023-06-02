package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.*;
import org.chemax.repository.*;
import org.chemax.request.FriendshipInviteRequest;
import org.chemax.request.DeleteFriendRequest;
import org.springframework.stereotype.Service;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    Logger log = Logger.getLogger(RelationshipServiceImpl.class.getName());

    private final SubscribedRepository subscribedRepository;

    private final FriendshipInviteRepository friendshipInviteRepository;

    private final FriendRepository friendRepository;

    private final SubscriberRepository subscriberRepository;

    public RelationshipServiceImpl(SubscribedRepository subscribedRepository,
                                   FriendshipInviteRepository friendshipInviteRepository,
                                   FriendRepository friendRepository, SubscriberRepository subscriberRepository) {
        this.subscribedRepository = subscribedRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
        this.friendRepository = friendRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public FriendshipInvite createFriendshipInviteAndSubscribe(FriendshipInviteRequest friendshipInviteRequest) {
        FriendshipInvite friendshipInvite = new FriendshipInvite();
        try {
            friendshipInvite = friendshipInviteRepository.save(buildFriendshipInviteFromRequest(friendshipInviteRequest));
            subscribeToUserWithUserId(friendshipInviteRequest.getRequesterId(), friendshipInviteRequest.getRequestedId());
        }
        catch (Exception ex) {
            log.error("Can't save object " + friendshipInviteRequest.toString());
        }
        return friendshipInvite;
    }

    @Override
    public Friend makeUsersFriendsAndSubcribersByFriendshipInviteId(Long inviteId) {
        Friend requester = new Friend();
        Friend requested = new Friend();

        try {
            FriendshipInvite friendshipInvite = friendshipInviteRepository.getReferenceById(inviteId);
            requester.setRequesterId(friendshipInvite.getRequesterId());
            requester.setRequestedId(friendshipInvite.getRequestedId());
            friendRepository.save(requester);

            requested.setRequesterId(friendshipInvite.getRequestedId());
            requested.setRequestedId(friendshipInvite.getRequesterId());
            friendRepository.save(requested);

            friendshipInviteRepository.deleteById(inviteId);
            subscribeToUserWithUserId(friendshipInvite.getRequestedId(), friendshipInvite.getRequesterId());
        }
        catch (Exception ex) {
            log.error("Can't save object " + requester);
        }
        return requester;
    }

    @Override
    public void deleteFrindshipInviteWithId(Long inviteId) {
        try {
            friendshipInviteRepository.deleteById(inviteId);
        }
        catch (Exception ex) {
            log.error("Can't delete object with id=" + inviteId);
        }
    }

    @Override
    public void deleteAndUnsubscribeFromRequestedUser(DeleteFriendRequest deleteFriendRequest) {
        try {
            friendRepository.delete((friendRepository
                    .findByRequesterIdAndRequestedId(deleteFriendRequest.getRequesterId(), deleteFriendRequest.getRequestedId())));
            friendRepository.delete(friendRepository
                    .findByRequesterIdAndRequestedId(deleteFriendRequest.getRequestedId(), deleteFriendRequest.getRequesterId()));
            subscriberRepository.delete(subscriberRepository
                    .findByRequesterIdAndRequestedId(deleteFriendRequest.getRequesterId(), deleteFriendRequest.getRequestedId()));
            subscribedRepository.delete(subscribedRepository
                    .findByRequesterIdAndRequestedId(deleteFriendRequest.getRequestedId(), deleteFriendRequest.getRequesterId()));
        }
        catch (Exception ex) {
            log.error("Can't delete objects");
        }
    }

    private FriendshipInvite buildFriendshipInviteFromRequest(FriendshipInviteRequest friendshipInviteRequest) {
        FriendshipInvite builtFriendshipInvite = new FriendshipInvite();
        builtFriendshipInvite.setRequesterId(friendshipInviteRequest.getRequesterId());
        builtFriendshipInvite.setRequestedId(friendshipInviteRequest.getRequestedId());

        return builtFriendshipInvite;
    }

    private void subscribeToUserWithUserId(Long requesterId, Long requestedId) {
        Subscriber subscriber = new Subscriber();
        Subscribed subscribed = new Subscribed();

        try {
            subscriber.setRequesterId(requesterId);
            subscriber.setRequestedId(requestedId);
            subscriberRepository.save(subscriber);

            subscribed.setRequesterId(requestedId);
            subscribed.setRequestedId(requesterId);
            subscribedRepository.save(subscribed);
        }
        catch (Exception ex) {
            log.error("Can't save object " + subscriber + "\n" + subscribed);
        }
    }
}
