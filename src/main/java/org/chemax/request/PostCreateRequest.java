package org.chemax.request;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.Map;

public class PostCreateRequest {

    private String header;
    private String message;
    private ZonedDateTime creationDateTime;
    private ZonedDateTime updatedDateTime;
    @Value("${upload.path}")
    private String uploadPath;
    private Long authorId;
    private Map<String, MultipartFile> files;

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

    public ZonedDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(ZonedDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public ZonedDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Map<String, MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(Map<String, MultipartFile> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "PostCreateRequest{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", authorId=" + authorId +
                ", files=" + files +
                '}';
    }
}
