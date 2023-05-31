package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.*;
import org.chemax.repository.*;
import org.chemax.request.FriendshipInviteRequest;
import org.chemax.request.UnsubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    Logger log = Logger.getLogger(RelationshipServiceImpl.class.getName());
    private final SubscribedRepository subscribedRepository;
    private final FriendshipInviteRepository friendshipInviteRepository;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;
    private final SubscriberRepository subscriberRepository;

    public RelationshipServiceImpl(SubscribedRepository subscribedRepository, FriendshipInviteRepository friendshipInviteRepository, UserRepository userRepository, FriendRepository friendRepository, SubscriberRepository subscriberRepository) {
        this.subscribedRepository = subscribedRepository;
        this.friendshipInviteRepository = friendshipInviteRepository;
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
        this.subscriberRepository = subscriberRepository;
    }

    public FriendshipInvite createFriendshipInviteAndSubscribe(FriendshipInviteRequest friendshipInviteRequest) {
        FriendshipInvite friendshipInvite = new FriendshipInvite();
        try {
            friendshipInvite = friendshipInviteRepository.save(buildFriendshipInviteFromRequest(friendshipInviteRequest));
            subscribeToUserWithUserId(friendshipInviteRequest.getRequester(), friendshipInviteRequest.getRequestedId());
        }
        catch (Exception ex) {
            log.error("Can't save object " + friendshipInviteRequest.toString());
        }
        return friendshipInvite;
    }

    @Override
    public Friend makeUsersFriendsAndSubcribersByFriendshipInviteId(Long inviteId) {
        Friend friendWhoSentInvite = new Friend();
        Friend friendWhoApproveInvite = new Friend();
        try {
            FriendshipInvite friendshipInvite = friendshipInviteRepository.getReferenceById(inviteId);
            friendWhoSentInvite.setUserWhoSentInvite(friendshipInvite.getRequester());
            friendWhoSentInvite.setWhoGetInviteUserId(friendshipInvite.getRequestedId());
            friendRepository.save(friendWhoSentInvite);
            friendWhoApproveInvite.setUserWhoSentInvite(userRepository.getReferenceById(friendshipInvite.getRequestedId()));
            friendWhoApproveInvite.setWhoGetInviteUserId(friendshipInvite.getRequester().getUserId());
            friendRepository.save(friendWhoApproveInvite);
            friendshipInviteRepository.deleteById(inviteId);
            subscribeToUserWithUserId(userRepository.getReferenceById(friendshipInvite.getRequestedId()),
                    friendshipInvite.getRequester().getUserId());
        }
        catch (Exception ex) {
            log.error("Can't save object " + friendWhoSentInvite);
        }
        return friendWhoSentInvite;
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
    public void unsubscribeFromRequestedUser(UnsubscribeRequest unsubscribeRequest) {
        try {
//            subscribedRepository.delete(subscribedRepository.findBySubscribedUserIdAndUserForSubscribe(8L, user));
        }
        catch (Exception ex) {
            log.error("Can't delete objects");
        }
    }

    private FriendshipInvite buildFriendshipInviteFromRequest(FriendshipInviteRequest friendshipInviteRequest) {
        FriendshipInvite builtFriendshipInvite = new FriendshipInvite();
        builtFriendshipInvite.setRequester(friendshipInviteRequest.getRequester());
        builtFriendshipInvite.setRequestedId(friendshipInviteRequest.getRequestedId());
        return builtFriendshipInvite;
    }

    private void subscribeToUserWithUserId(User userWhoSubscribes, Long userId) {
        Subscriber subscriber = new Subscriber();
        Subscribed subscribed = new Subscribed();
        try {
            subscriber.setUserWhoSubscribes(userWhoSubscribes);
            subscriber.setTargetForSubscribeUserId(userId);
            subscriberRepository.save(subscriber);
            subscribed.setUserForSubscribe(userRepository.getReferenceById(userId));
            subscribed.setSubscribedUserId(userWhoSubscribes.getUserId());
            subscribedRepository.save(subscribed);
        }
        catch (Exception ex) {
            log.error("Can't save object " + subscriber + "\n" + subscribed);
        }
    }
}
