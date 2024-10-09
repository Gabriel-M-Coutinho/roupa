package com.wowcreations.roupaapi.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException() {
        super("The username already exists.");
    }
}
