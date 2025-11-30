package com.gabeDev.BankRestAPI.exceptions.account_holder;

public class AccountHolderNotFoundException extends RuntimeException{

    public AccountHolderNotFoundException(Long id){
        super("Account Holder with id " + id + " not found");
    }
}
