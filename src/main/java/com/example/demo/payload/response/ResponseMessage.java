package com.example.demo.payload.response;

public class ResponseMessage {
    public Boolean success;
    public Object message;

    public ResponseMessage(Boolean success, Object message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
