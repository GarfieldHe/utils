package com.utils.work.http;

public class GlobalException extends RuntimeException {
    protected static final long serialVersionUID = 5796897502988086681L;
    private Integer code;
    private String path;

    public GlobalException(String code, String message) {
        super(message);
        this.code = Integer.parseInt(code);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
