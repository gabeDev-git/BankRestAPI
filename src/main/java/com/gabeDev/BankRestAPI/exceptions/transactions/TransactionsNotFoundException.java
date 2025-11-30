package com.gabeDev.BankRestAPI.exceptions.transactions;

public class TransactionsNotFoundException extends RuntimeException {

    public TransactionsNotFoundException(Long transactionsId){
        super("Transaction of id " + transactionsId + " not found");
    }
}
