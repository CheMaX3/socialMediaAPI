package org.chemax.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friendship_invites")
public class FriendshipInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invite_id")
    private Long inviteId;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "requested_id")
    private Long requestedId;

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(Long requestedId) {
        this.requestedId = requestedId;
    }

    @Override
    public String toString() {
        return "FriendshipInvite{" +
                "inviteId=" + inviteId +
                ", requesterId=" + requesterId +
                ", requestedId=" + requestedId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipInvite that = (FriendshipInvite) o;
        return Objects.equals(inviteId, that.inviteId) && Objects.equals(requesterId, that.requesterId) && Objects.equals(requestedId, that.requestedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inviteId, requesterId, requestedId);
    }
}
