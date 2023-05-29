package org.chemax.request;

import org.chemax.entity.Image;

import java.time.ZonedDateTime;
import java.util.List;

public class PostUpdateRequest {
    private String header;
    private String message;
    private List<Image> content;
    private ZonedDateTime updatedDateTime;

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

    public ZonedDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(ZonedDateTime updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }
}
