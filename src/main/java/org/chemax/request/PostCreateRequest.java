package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Запрос на создание нового поста")
public class PostCreateRequest {

    @Schema(description = "Заголовок поста")
    @NotNull
    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    private String header;

    @Schema(description = "Текст поста")
    @NotNull
    private String message;

    @Schema(description = "Id автора поста")
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
