package org.chemax.repository;

import org.chemax.entity.Subscribed;
import org.chemax.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SubscribedRepository extends JpaRepository<Subscribed, Long> {
    void deleteBySubscribedUserIdAndUserForSubscribe(Long subscribedUserId, User userForSubscribe);
    Subscribed findBySubscribedUserIdAndUserForSubscribe(Long subscribedUserId, User userForSubscribe);
}
