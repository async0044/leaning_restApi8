package com.async.restApi8.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionDto(
        HttpStatus status,
        String message,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp) {

    public ExceptionDto(HttpStatus status, String message) {
        this(status, message, LocalDateTime.now());
        //if (timestamp == null) timestamp = LocalDateTime.now();
    }
}
