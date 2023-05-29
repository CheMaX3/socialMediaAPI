package org.chemax.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "subscriber_id")
    private Long subscriberId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Long subscriberId) {
        this.subscriberId = subscriberId;
    }
}
