package org.chemax.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCreateRequest {

    @NotNull(message = "Поле заголовок не может быть пустым")
    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    private String header;

    @NotNull(message = "Нужно указать текст сообщения")
    private String message;

    @NotNull
    private Long authorId;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "PostCreateRequest{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
