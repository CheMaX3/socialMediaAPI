package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.*;
import org.chemax.exception.FieldCantBeEmptyException;
import org.chemax.repository.*;
import org.chemax.request.FriendshipInviteRequest;
import org.chemax.request.DeleteFriendRequest;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

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
        try {
            FriendshipInvite friendshipInvite = buildFriendshipInviteFromRequest(friendshipInviteRequest);
            friendshipInviteRepository.save(friendshipInvite);
            subscribeToUserWithUserId(friendshipInviteRequest.getRequesterId(), friendshipInviteRequest.getRequestedId());

            return friendshipInvite;

        }
        catch (Exception ex) {
            log.error("Can't save object " + friendshipInviteRequest.toString());
            throw new DataSourceLookupFailureException("Can't save object " + friendshipInviteRequest);
        }
    }

    @Override
    public void deleteFriendshipInviteWithId(Long inviteId) {
        try {
            friendshipInviteRepository.deleteById(inviteId);
        }
        catch (Exception ex) {
            log.error("friendshipInvite with inviteId=" + inviteId + " not exists");
            throw new EntityNotFoundException("friendshipInvite with inviteId=" + inviteId + " not exists");
        }
    }

    @Override
    public boolean makeUsersFriendsAndSubscribersByFriendshipInviteId(Long inviteId) {
        try {
            Friend requester = new Friend();
            Friend requested = new Friend();

            FriendshipInvite friendshipInvite = friendshipInviteRepository.getReferenceById(inviteId);
            requester.setRequesterId(friendshipInvite.getRequesterId());
            requester.setRequestedId(friendshipInvite.getRequestedId());
            friendRepository.save(requester);

            requested.setRequesterId(friendshipInvite.getRequestedId());
            requested.setRequestedId(friendshipInvite.getRequesterId());
            friendRepository.save(requested);

            friendshipInviteRepository.deleteById(inviteId);
            subscribeToUserWithUserId(friendshipInvite.getRequestedId(), friendshipInvite.getRequesterId());

            return (friendRepository.findByRequesterIdAndRequestedId(requester.getRequesterId(),
                    requested.getRequesterId()) != null);
        }
        catch (Exception ex) {
            log.error("Can't save object");
            throw new DataSourceLookupFailureException("Can't save object");
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
            throw new DataSourceLookupFailureException("Can't delete objects");
        }
    }

    private void subscribeToUserWithUserId(Long requesterId, Long requestedId) {
        try {
            Subscriber subscriber = new Subscriber();
            Subscribed subscribed = new Subscribed();

            subscriber.setRequesterId(requesterId);
            subscriber.setRequestedId(requestedId);
            subscriberRepository.save(subscriber);

            subscribed.setRequesterId(requestedId);
            subscribed.setRequestedId(requesterId);
            subscribedRepository.save(subscribed);
        }
        catch (Exception ex) {
            log.error("Can't save objects");
            throw new DataSourceLookupFailureException("Can't save objects");
        }
    }

    private FriendshipInvite buildFriendshipInviteFromRequest(FriendshipInviteRequest friendshipInviteRequest) {
        try {
            FriendshipInvite builtFriendshipInvite = new FriendshipInvite();
            builtFriendshipInvite.setRequesterId(Optional.ofNullable(friendshipInviteRequest.getRequesterId())
                    .orElseThrow(FieldCantBeEmptyException::new));
            builtFriendshipInvite.setRequestedId(Optional.ofNullable(friendshipInviteRequest.getRequestedId())
                    .orElseThrow(FieldCantBeEmptyException::new));

            return builtFriendshipInvite;

        }
        catch (Exception ex) {
            log.error("Bad request " + friendshipInviteRequest.toString());
            throw new FieldCantBeEmptyException();
        }
    }
}
