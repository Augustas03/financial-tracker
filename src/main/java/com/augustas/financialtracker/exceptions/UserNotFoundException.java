package com.augustas.financialtracker.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){ super(message); }
}
