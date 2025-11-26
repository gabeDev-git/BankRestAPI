package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.AccountHolderNotFoundException;
import com.gabeDev.BankRestAPI.exceptions.InvalidAgeException;
import com.gabeDev.BankRestAPI.mapper.AccountHolderMapper;
import com.gabeDev.BankRestAPI.repository.AccountHolderRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountHolderService {

    private final AccountHolderRepo accountHolderRepo;
    private final PasswordEncoder encoder;

    public AccountHolderService(AccountHolderRepo accountHolderRepo,
                                PasswordEncoder encoder) {
        this.accountHolderRepo = accountHolderRepo;
        this.encoder = encoder;
    }

    @Transactional
    public AccountHolder create(AccountHolderPostRequest request) {
        AccountHolder holder = AccountHolderMapper.toEntity(request);
        validAge(holder);

            Wallet wallet = new Wallet();
            wallet.setBalance(BigDecimal.valueOf(0));
            wallet.setTransactionsCount(0L);
            wallet.setHolder(holder);
            wallet.setCreatedAt(LocalDateTime.now());
            wallet.setUpdatedAt(LocalDateTime.now());
            holder.setWallet(wallet);
            holder.setPassword(encoder.encode(holder.getPassword()));
            return accountHolderRepo.save(holder);
    }

    private void validAge(AccountHolder holder){
        if (holder.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidAgeException();
        }
    }

    public List<AccountHolder> getAllHolders(){
        return accountHolderRepo.findAll();
    }

    public AccountHolder findById(Long id){
        return accountHolderRepo.findById(id)
                .orElseThrow(() -> new AccountHolderNotFoundException(id));
    }
}
