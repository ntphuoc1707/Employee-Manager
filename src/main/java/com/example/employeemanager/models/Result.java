package com.example.employeemanager.models;

public class Result {
    private Boolean status;
    private Object content;
    private String message;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public Object getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public Result(Boolean status, Object content, String message) {
        this.status = status;
        this.content = content;
        this.message = message;
    }
}
