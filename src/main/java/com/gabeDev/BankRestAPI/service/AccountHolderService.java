package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderPutRequest;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.account_holder.AccountHolderNotFoundException;
import com.gabeDev.BankRestAPI.exceptions.account_holder.InvalidAgeException;
import com.gabeDev.BankRestAPI.mapper.AccountHolderMapper;
import com.gabeDev.BankRestAPI.repository.AccountHolderRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
            wallet.setHolder(holder);
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

    public AccountHolder update(Long id, AccountHolderPutRequest request) {
        var holder = findById(id);

        if (request.fullName() != null && !request.fullName().isBlank()) {
            holder.setFullName(request.fullName());
        }
        if (request.email() != null && !request.email().isBlank()) {
            holder.setEmail(request.email());
        }
        if (request.password() != null && !request.password().isBlank()) {
            holder.setPassword(encoder.encode(request.password()));
        }

        holder.setUpdatedAt(LocalDateTime.now());
        return accountHolderRepo.save(holder);
    }

    public void deleteById(Long id){
        var holder = findById(id);
        accountHolderRepo.deleteById(id);
    }
}
