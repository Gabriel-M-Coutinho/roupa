package com.wowcreations.roupaapi.exceptions;

public class NameLengthException extends RuntimeException {
    public NameLengthException() {
        super("Your name must contain at least 3 letters.");
    }
}
