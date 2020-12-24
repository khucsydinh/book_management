package com.example.demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private Boolean status; // 0 - success, 1 - failed
    private String message; // message
    private long timestamp;
}
