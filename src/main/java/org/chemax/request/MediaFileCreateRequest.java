package org.chemax.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class MediaFileCreateRequest {

    @NotNull
    private String filename;
    @NotNull
    private MultipartFile content;
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
