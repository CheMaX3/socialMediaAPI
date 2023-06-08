package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "Запрос на изменение поста")
public class PostUpdateRequest {

    @NotNull
    @Schema(description = "Заголовок поста")
    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    private String header;

    @NotNull
    @Schema(description = "Текст поста")
    private String message;

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

    @Override
    public String toString() {
        return "PostUpdateRequest{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
