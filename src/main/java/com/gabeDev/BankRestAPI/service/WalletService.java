package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.WalletNotFoundException;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepo walletRepo;

    public WalletService(WalletRepo walletRepo){
        this.walletRepo = walletRepo;
    }

    public Wallet findById(Long id){
        return walletRepo.findById(id)
                .orElseThrow(() -> new WalletNotFoundException(id));
    }

    public Wallet deposit(BigDecimal amount, Long walletId){
        var wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Deposit can't be 0 or less");
        }

        wallet.setBalance(wallet.getBalance().add(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTransactionsCount(wallet.getTransactionsCount() + 1);
        return walletRepo.save(wallet);
    }

    public Wallet debit(BigDecimal amount, Long walletId){
        var wallet = walletRepo.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        if(wallet.getBalance().compareTo(amount) < 0 || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Insufficient balance or invalid amount");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTransactionsCount(wallet.getTransactionsCount() + 1);
        return walletRepo.save(wallet);
    }

    public List<Wallet> findAll(){
        return walletRepo.findAll();
    }

}
