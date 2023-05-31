package org.chemax.entity;

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friend {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userWhoSentInvite;

    @Id
    @Column(name = "friend_id")
    private Long whoGetInviteUserId;

    public User getUserWhoSentInvite() {
        return userWhoSentInvite;
    }

    public void setUserWhoSentInvite(User userWhoSentInvite) {
        this.userWhoSentInvite = userWhoSentInvite;
    }

    public Long getWhoGetInviteUserId() {
        return whoGetInviteUserId;
    }

    public void setWhoGetInviteUserId(Long whoGetInviteUserId) {
        this.whoGetInviteUserId = whoGetInviteUserId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userWhoSentInvite=" + userWhoSentInvite +
                ", friendId=" + whoGetInviteUserId +
                '}';
    }
}
