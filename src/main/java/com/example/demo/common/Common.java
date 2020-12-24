package com.example.demo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Map;

public class Common {
    public static Long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static boolean isNullOrEmpty(Object obj) {

        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return obj.toString().isEmpty();
        }
        if (obj instanceof List) {
            return ((List) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    public static String createMessageLog(Object input, Object response, Long timeStamp, String methodName) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        ObjectMapper objectMapper = new ObjectMapper();
        sb.append(timeStamp + "");
        try {
            if (!isNullOrEmpty(input))
                sb.append("_" + objectMapper.writeValueAsString(input));
            if (!isNullOrEmpty(methodName))
                sb.append("_" + gson.toJson(methodName));
            if (!isNullOrEmpty(response)) {
                sb.append("_" + objectMapper.writeValueAsString(response));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return sb.toString();
    }
}
