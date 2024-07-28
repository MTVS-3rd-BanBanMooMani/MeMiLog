package com.banbanmoomani.memilog.config;

public class Message {

    private String message;
    private String href;

    public Message() {
    }

    public Message(String message, String href) {
        this.message = message;
        this.href = href;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
