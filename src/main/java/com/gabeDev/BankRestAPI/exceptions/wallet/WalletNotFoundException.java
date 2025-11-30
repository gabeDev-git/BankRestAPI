package com.gabeDev.BankRestAPI.exceptions.wallet;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(Long id) {
        super("Wallet with id " + id + " not found");
    }
}
