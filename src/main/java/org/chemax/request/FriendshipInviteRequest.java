package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class FriendshipInviteRequest {

    @NotNull
    @Schema(description = "Id пользователя, который отправляет приглашение в друзья")
    private Long requesterId;

    @NotNull
    @Schema(description = "Id пользователя, которому отправляется приглашение в друзья")
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

    @Override
    public String toString() {
        return "FriendshipInviteRequest{" +
                "requesterId=" + requesterId +
                ", requestedId=" + requestedId +
                '}';
    }
}
