package com.gabeDev.BankRestAPI.exceptions.transactions;


public class SameSenderAndReceiverException extends RuntimeException{

    public SameSenderAndReceiverException(Long senderWalletId, Long receiverWalletId){
        super("Sender wallet id " + senderWalletId + " and receiver wallet id " + receiverWalletId
        + " are the same.");
    }
}
