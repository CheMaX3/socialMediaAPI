package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Запрос на удаление пользователя из списка друзей")
public class DeleteFriendRequest {

    @NotNull
    @Schema(description = "Id пользователя, инициирующего удаление")
    private Long requesterId;

    @NotNull
    @Schema(description = "Id пользователя, который удаляется из списка друзей")
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
