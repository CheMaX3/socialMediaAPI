package org.chemax.repository;

import org.chemax.entity.FriendshipInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipInviteRepository extends JpaRepository<FriendshipInvite, Long> {
    List<FriendshipInvite> findFriendshipInvitesByRequestedId(Long requestedId);
}
