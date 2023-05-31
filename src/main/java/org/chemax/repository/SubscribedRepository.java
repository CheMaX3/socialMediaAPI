package org.chemax.repository;

import org.chemax.entity.Subscribed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribedRepository extends JpaRepository<Subscribed, Long> {
    Subscribed findByRequesterIdAndRequestedId(Long requesterId, Long requestedId);
    List<Subscribed> findByRequesterId(Long requesterId);
}
