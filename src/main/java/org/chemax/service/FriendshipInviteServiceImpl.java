package org.chemax.service;

import org.apache.log4j.Logger;
import org.chemax.entity.FriendshipInvite;
import org.chemax.repository.FriendshipInviteRepository;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipInviteServiceImpl implements FriendshipInviteService {

    private static final Logger log = Logger.getLogger(FriendshipInviteServiceImpl.class.getName());

    private final FriendshipInviteRepository friendshipInviteRepository;

    public FriendshipInviteServiceImpl(FriendshipInviteRepository friendshipInviteRepository) {
        this.friendshipInviteRepository = friendshipInviteRepository;
    }

    @Override
    public List<FriendshipInvite> getFriendshipInvitesListByRequestedId(Long requestedId) {
        try {
            return friendshipInviteRepository.findFriendshipInvitesByRequestedId(requestedId);
        }
        catch (Exception ex) {
            log.error("Can't retrieve objects from DB");
            throw new DataSourceLookupFailureException("Can't retrieve objects from DB");
        }
    }
}
