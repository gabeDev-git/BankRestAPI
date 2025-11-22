package com.gabeDev.BankRestAPI.mapper;

import com.gabeDev.BankRestAPI.dto.TransactionsPostRequest;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.entity.Wallet;

import java.time.LocalDateTime;

public class TransactionsMapper {


    public static Transactions toEntity(TransactionsPostRequest request, Wallet sender, Wallet receiver){
        Transactions transactions = new Transactions();
        transactions.setType(request.type());
        transactions.setSenderWallet(sender);
        transactions.setReceiverWallet(receiver);
        transactions.setAmount(request.amount());
        transactions.setCreatedAt(LocalDateTime.now());

        return transactions;
    }
}
