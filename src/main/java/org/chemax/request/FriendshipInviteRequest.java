package org.chemax.request;

import org.chemax.entity.User;

public class FriendshipInviteRequest {

    private User requester;
    private Long requestedId;

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public Long getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(Long requestedId) {
        this.requestedId = requestedId;
    }

    @Override
    public String toString() {
        return "FriendshipInviteRequest{" +
                "requester=" + requester +
                ", requestedId=" + requestedId +
                '}';
    }
}
