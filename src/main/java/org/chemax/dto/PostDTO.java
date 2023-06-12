package org.chemax.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(description = "Сущность поста")
public class PostDTO {

    @NotEmpty
    @Schema(description = "Id поста")
    private Long postId;

    @NotEmpty
    @Schema(description = "Заголовок поста")
    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    private String header;

    @NotEmpty
    @Schema(description = "Текст поста")
    private String message;

    @NotEmpty
    @Schema(description = "Id автора поста")
    private Long authorId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

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
}
