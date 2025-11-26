package com.gabeDev.BankRestAPI.exceptions;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException(){
        super("Insufficient funds to complete transaction");
    }
}
