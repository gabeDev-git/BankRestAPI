package com.gabeDev.BankRestAPI.exceptions;

public class InvalidAgeException extends RuntimeException{

    public InvalidAgeException(){
        super("User must be 18 or older to create an account");
    }
}
