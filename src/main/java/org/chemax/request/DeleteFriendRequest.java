package org.chemax.request;

import javax.validation.constraints.NotNull;

public class DeleteFriendRequest {

    @NotNull
    private Long requesterId;
    @NotNull
    private Long requestedId;

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
}
