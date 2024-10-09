package com.wowcreations.roupaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandling {
    @ResponseBody
    @ExceptionHandler(NameLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String nameLengthException(NameLengthException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String usernameExistsException(UsernameExistsException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(PasswordLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordLengthException(PasswordLengthException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TokenCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tokenCreationException(TokenCreationException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TokenValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String tokenValidationException(TokenValidationException ex) {
        return ex.getMessage();
    }
}
