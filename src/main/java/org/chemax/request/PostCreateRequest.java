package org.chemax.request;

import org.chemax.entity.User;

import java.time.ZonedDateTime;

public class PostCreateRequest {

    private String header;
    private String message;
    private String filePath;
    private ZonedDateTime creationDateTime;
    private ZonedDateTime updatedDateTime;
    private User author;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "PostCreateRequest{" +
                "header='" + header + '\'' +
                ", message='" + message + '\'' +
                ", filePath='" + filePath + '\'' +
                ", creationDateTime=" + creationDateTime +
                ", updatedDateTime=" + updatedDateTime +
                ", author=" + author +
                '}';
    }
}
