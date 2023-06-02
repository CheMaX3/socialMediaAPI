package org.chemax.repository;

import org.chemax.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Subscriber findByRequesterIdAndRequestedId(Long requesterId, Long requestedId);

    List<Subscriber> findByRequestedId(Long requestedId);

    List<Subscriber> findByRequesterId(Long requesterId);
}
