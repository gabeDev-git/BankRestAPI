package com.gabeDev.BankRestAPI.exceptions.transactions;

import com.gabeDev.BankRestAPI.entity.TransactionsType;

public class InvalidTransactionsTypeException extends RuntimeException{

    public InvalidTransactionsTypeException(TransactionsType type){
        super("Invalid transaction type! Please choose: " + type.toString());
    }
}
