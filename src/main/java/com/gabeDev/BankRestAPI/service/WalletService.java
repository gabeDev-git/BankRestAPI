package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletService {

    private final WalletRepo walletRepo;

    public WalletService(WalletRepo walletRepo){
        this.walletRepo = walletRepo;
    }

    public Wallet findById(Long id){
        return walletRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public Wallet deposit(BigDecimal amount, Long walletId){
        var wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTransactionsCount(wallet.getTransactionsCount() + 1);
        return walletRepo.save(wallet);
    }

    public Wallet debit(BigDecimal amount, Long walletId){
        var wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));

        wallet.setBalance(wallet.getBalance().subtract(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTransactionsCount(wallet.getTransactionsCount() + 1);
        return walletRepo.save(wallet);
    }
}
