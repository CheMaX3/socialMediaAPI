package org.chemax.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userWhoSubscribes;

    @Id
    @Column(name = "subscriber_id")
    private Long targetForSubscribeUserId;

    public User getUserWhoSubscribes() {
        return userWhoSubscribes;
    }

    public void setUserWhoSubscribes(User userWhoSubscribes) {
        this.userWhoSubscribes = userWhoSubscribes;
    }

    public Long getTargetForSubscribeUserId() {
        return targetForSubscribeUserId;
    }

    public void setTargetForSubscribeUserId(Long targetForSubscribeUserId) {
        this.targetForSubscribeUserId = targetForSubscribeUserId;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "userWhoSubscribes=" + userWhoSubscribes +
                ", targetForSubscribeUserId=" + targetForSubscribeUserId +
                '}';
    }
}
