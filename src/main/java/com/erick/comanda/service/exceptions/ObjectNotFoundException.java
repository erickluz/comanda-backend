package com.erick.comanda.service.exceptions;

public class ObjectNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }
}