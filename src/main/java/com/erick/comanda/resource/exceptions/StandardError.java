package com.erick.comanda.resource.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StandardError {
    private final String timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

    public StandardError(Integer status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }    

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}