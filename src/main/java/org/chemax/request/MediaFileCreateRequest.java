package org.chemax.request;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Schema(description = "Запрос на сохранение медиафайла в базе")
public class MediaFileCreateRequest {

    @Schema(description = "Имя файла")
    @NotNull
    private String filename;

    @Schema(description = "Сериализуемый медиафайл")
    @NotNull
    private MultipartFile content;

    @Schema(description = "Id поста, к которому прикрепляется медиафайл")
    @NotNull
    private Long postId;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public MultipartFile getContent() {
        return content;
    }

    public void setContent(MultipartFile content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "MediaFileCreateRequest{" +
                "filename='" + filename + '\'' +
                ", content=" + content +
                ", postId=" + postId +
                '}';
    }
}
