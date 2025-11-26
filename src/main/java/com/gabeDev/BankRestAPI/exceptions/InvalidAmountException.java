package com.gabeDev.BankRestAPI.exceptions;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(){
        super("Amount must not be null or lower than 0");
    }
}
