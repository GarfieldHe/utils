//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.utils.work.http;


public class GlobalMessage {
    private String code;
    private String message;

    public GlobalMessage() {
    }

    public GlobalMessage(String message) {
        this.code = "0";
        this.message = message;
    }

    /** @deprecated */
    @Deprecated
    public GlobalMessage(int code, String message) {
        this.code = String.valueOf(code);
        this.message = message;
    }

    public GlobalMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
