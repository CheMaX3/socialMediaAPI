package org.chemax.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "requester_id")
    private Long requesterId;

    @Column(name = "requested_id")
    private Long requestedId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Friend{" +
                "id=" + id +
                ", requesterId=" + requesterId +
                ", requestedId=" + requestedId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return Objects.equals(id, friend.id) && Objects.equals(requesterId, friend.requesterId) && Objects.equals(requestedId, friend.requestedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requesterId, requestedId);
    }
}
