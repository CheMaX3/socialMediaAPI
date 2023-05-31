package org.chemax.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribed")
public class Subscribed {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userForSubscribe;

    @Id
    @Column(name = "subscribed_id")
    private Long subscribedUserId;

    public User getUserForSubscribe() {
        return userForSubscribe;
    }

    public void setUserForSubscribe(User userForSubscribe) {
        this.userForSubscribe = userForSubscribe;
    }

    public Long getSubscribedUserId() {
        return subscribedUserId;
    }

    public void setSubscribedUserId(Long subscribedUserId) {
        this.subscribedUserId = subscribedUserId;
    }

    @Override
    public String toString() {
        return "Subscribed{" +
                "userToSubscribe=" + userForSubscribe +
                ", subscriberUserId=" + subscribedUserId +
                '}';
    }
}
