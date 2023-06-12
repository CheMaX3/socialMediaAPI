package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.Friend;
import org.chemax.repository.FriendRepository;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    private static final Logger log = Logger.getLogger(FriendServiceImpl.class.getName());

    private final FriendRepository friendRepository;

    public FriendServiceImpl(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    @Override
    public List<Friend> getFriendListByRequesterId(Long requesterId) {
        try {
            return friendRepository.findByRequesterId(requesterId);
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }

    @Override
    public boolean friendshipCheck(Long senderId, Long receiverId) {
        try {
            return (friendRepository.findByRequesterIdAndRequestedId(senderId, receiverId) != null);
        }
        catch (Exception ex) {
            log.error("Can't retrieve object from DB");
            throw new EntityNotFoundException("Can't retrieve object from DB");
        }
    }
}
