package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.InsufficientFundsException;
import com.gabeDev.BankRestAPI.exceptions.InvalidAmountException;
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
        Wallet wallet = findById(walletId);
        positiveAmountCheck(amount);
        wallet.setBalance(wallet.getBalance().add(amount));
        return updateWallet(wallet);
    }

    public Wallet debit(BigDecimal amount, Long walletId){
        Wallet wallet = findById(walletId);
        positiveAmountCheck(amount);
        sufficientFundsCheck(amount, wallet);

        wallet.setBalance(wallet.getBalance().subtract(amount));
        return updateWallet(wallet);
    }

    public List<Wallet> findAll(){
        return walletRepo.findAll();
    }

    private void positiveAmountCheck(BigDecimal amount){
        if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException();
        }
    }

    private void sufficientFundsCheck(BigDecimal amount, Wallet wallet){
        if(amount == null){
            throw new InvalidAmountException();
        }
        if(wallet.getBalance().compareTo(amount) < 0){
            throw new InsufficientFundsException();
        }
    }

    private Wallet updateWallet(Wallet wallet){
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setTransactionsCount(wallet.getTransactionsCount() + 1);
        return walletRepo.save(wallet);
    }

}
