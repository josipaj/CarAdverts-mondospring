package com.example.springmongodbdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(List<String> message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("validation_errors", message);

            return new ResponseEntity<Object>(map,status);
    }
}