package com.utils.work.http;

public class GlobalExceptionDTO {
    private Integer code;
    private String message;
    private String path;
    private long timestamp;

    public GlobalExceptionDTO() {
        this.timestamp = System.currentTimeMillis();
    }

    public GlobalExceptionDTO(GlobalException exception) {
        this();
        this.code = exception.getCode();
        this.message = exception.getMessage();
        this.path = exception.getPath();
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

