package com.gabeDev.BankRestAPI.mapper;
import com.gabeDev.BankRestAPI.dto.transactions.*;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.entity.Wallet;
import java.time.LocalDateTime;

public class TransactionsMapper {


    public static Transactions toTransfer(TransferRequest request, Wallet sender, Wallet receiver){
        Transactions transactions = new Transactions();
        transactions.setType(request.type());
        transactions.setSenderWallet(sender);
        transactions.setReceiverWallet(receiver);
        transactions.setAmount(request.amount());
        transactions.setCreatedAt(LocalDateTime.now());

        return transactions;
    }

    public static Transactions toDeposit(DepositRequest request, Wallet receiver){
        Transactions transactions = new Transactions();
        transactions.setType(request.type());
        transactions.setReceiverWallet(receiver);
        transactions.setAmount(request.amount());
        transactions.setCreatedAt(LocalDateTime.now());

        return transactions;
    }

    public static Transactions toDebit(DebitRequest request, Wallet sender){
        Transactions transactions = new Transactions();
        transactions.setType(request.type());
        transactions.setSenderWallet(sender);
        transactions.setAmount(request.amount());
        transactions.setCreatedAt(LocalDateTime.now());

        return transactions;
    }


    public static TransferResponse toTransferResponse(Transactions transactions){
        return new TransferResponse(transactions.getId(), transactions.getSenderWallet().getId(),
                transactions.getSenderWallet().getHolder().getFullName(),
                transactions.getReceiverWallet().getId(),
                transactions.getReceiverWallet().getHolder().getFullName(),
                transactions.getAmount(),
                transactions.getCreatedAt(), transactions.getType());
    }

    public static DepositResponse toDepositResponse(Transactions transactions){
        return new DepositResponse(transactions.getId(), transactions.getReceiverWallet().getId(),
                transactions.getReceiverWallet().getHolder().getFullName(),
                transactions.getAmount(), transactions.getCreatedAt(), transactions.getType());
    }

    public static DebitResponse toDebitResponse(Transactions transactions){
        return new DebitResponse(transactions.getId(), transactions.getSenderWallet().getId(),
                transactions.getSenderWallet().getHolder().getFullName(),
                transactions.getAmount(), transactions.getCreatedAt(), transactions.getType());
    }

    public static TransactionsResponse toGenericResponse(Transactions transactions) {
        return switch (transactions.getType()){
            case DEPOSIT -> toDepositResponse(transactions);
            case DEBIT -> toDebitResponse(transactions);
            case TRANSFER -> toTransferResponse(transactions);
        };
    }

}

