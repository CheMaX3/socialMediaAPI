package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Schema(description = "Запрос на создание личного сообщения")
public class MessageCreateRequest {

    @Schema(description = "Текст отправляемого сообщения", example = "Привет. Как дела?")
    @NotNull
    private String text;

    @Schema(description = "Id пользователя, отправляющего сообщение")
    @NotNull
    private Long senderId;

    @Schema(description = "Id пользователя, получающего сообщение")
    @NotNull
    private Long receiverId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "MessageCreateRequest{" +
                "text='" + text + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageCreateRequest that = (MessageCreateRequest) o;
        return Objects.equals(text, that.text) && Objects.equals(senderId, that.senderId) && Objects.equals(receiverId, that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, senderId, receiverId);
    }
}
