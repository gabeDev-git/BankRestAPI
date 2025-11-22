package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.TransactionsPostRequest;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.entity.TransactionsType;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.mapper.TransactionsMapper;
import com.gabeDev.BankRestAPI.repository.TransactionsRepo;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    private final WalletService walletService;
    private final TransactionsRepo transactionsRepo;
    private final WalletRepo walletRepo;

    public TransactionsService(WalletService walletService, TransactionsRepo transactionsRepo,
                               WalletRepo walletRepo){
        this.walletService = walletService;
        this.transactionsRepo = transactionsRepo;
        this.walletRepo = walletRepo;
    }

    @Transactional
    public Transactions deposit(TransactionsPostRequest request){
        if(request.type() != TransactionsType.DEPOSIT){
            throw new RuntimeException(("Transaction type is not DEPOSIT"));
        }
        Wallet receiverWallet = walletService.deposit(request.amount(), request.receiverId());
        return TransactionsMapper.toEntity(request, null, receiverWallet);
    }
}
