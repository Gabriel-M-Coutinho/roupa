package com.wowcreations.roupaapi.exceptions;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(String msg) {
        super(msg);
    }
}
