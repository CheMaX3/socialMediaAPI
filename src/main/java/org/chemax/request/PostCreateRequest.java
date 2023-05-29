package org.chemax.request;

import org.chemax.entity.Image;
import org.chemax.entity.User;

import java.time.ZonedDateTime;
import java.util.List;

public class PostCreateRequest {

    private String header;
    private String message;
    private List<Image> content;
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

    public List<Image> getContent() {
        return content;
    }

    public void setContent(List<Image> content) {
        this.content = content;
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
}
