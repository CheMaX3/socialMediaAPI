package org.chemax.repository;

import org.chemax.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    void deleteByTargetForSubscribeUserId(Long targetForSubscribeUserId);
}
