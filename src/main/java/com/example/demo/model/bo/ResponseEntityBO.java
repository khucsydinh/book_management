package com.example.demo.model.bo;

import lombok.Data;

@Data
public class ResponseEntityBO<T> extends Message {
    private T result;

    public ResponseEntityBO(Boolean errorResponse, String s, long timeStamp, T result) {
        super(errorResponse, s, timeStamp);
        this.result = result;
    }
}
