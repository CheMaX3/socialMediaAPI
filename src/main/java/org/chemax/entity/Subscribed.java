package org.chemax.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribed")
public class Subscribed {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "subscribed_id")
    private Long subscribedId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSubscribedId() {
        return subscribedId;
    }

    public void setSubscribedId(Long subscribedId) {
        this.subscribedId = subscribedId;
    }
}
