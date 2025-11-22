package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.dto.AccountHolderResponse;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.mapper.AccountHolderMapper;
import com.gabeDev.BankRestAPI.repository.AccountHolderRepo;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountHolderService {

    private final AccountHolderRepo accountHolderRepo;
    private final WalletRepo walletRepo;

    public AccountHolderService(AccountHolderRepo accountHolderRepo, WalletRepo walletRepo) {
        this.accountHolderRepo = accountHolderRepo;
        this.walletRepo = walletRepo;
    }

    @Transactional
    public AccountHolder create(AccountHolderPostRequest request) {
        AccountHolder holder = AccountHolderMapper.toEntity(request);

        if(validAge(holder)) {

            Wallet wallet = new Wallet();
            wallet.setBalance(BigDecimal.valueOf(0));
            wallet.setTransactionsCount(0L);
            wallet.setHolder(holder);
            wallet.setCreatedAt(LocalDateTime.now());
            wallet.setUpdatedAt(LocalDateTime.now());
            holder.setWallet(wallet);
            walletRepo.save(wallet);

            return accountHolderRepo.save(holder);
        }
        else{
            throw new RuntimeException("User must be at least 18 years old to register");
        }
    }

    public boolean validAge(AccountHolder holder){
        LocalDate requiredDate = LocalDate.now().minusYears(18);
        if(holder.getBirthDate().isAfter(requiredDate)){
            return false;
        }
        return true;
    }

    public List<AccountHolder> getAllHolders(){
        return accountHolderRepo.findAll();
    }

    public AccountHolder findById(Long id){
        var entity = accountHolderRepo.findById(id);

        if(entity.isPresent()){
            return entity.get();
        }
        else{
            throw new RuntimeException("Entity not found");
        }
    }
}
