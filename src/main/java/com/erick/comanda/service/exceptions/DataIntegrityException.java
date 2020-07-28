package com.erick.comanda.service.exceptions;

public class DataIntegrityException extends Exception{
    private static final long serialVersionUID = 3053724504261168874L;

    public DataIntegrityException(String message) {
        super(message);
    }
}