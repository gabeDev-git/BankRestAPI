package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderPostRequest;

import com.gabeDev.BankRestAPI.exceptions.account_holder.InvalidAgeException;
import com.gabeDev.BankRestAPI.repository.AccountHolderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountHolderServiceTest {

    @Mock
    private AccountHolderRepo accountHolderRepo;

    @InjectMocks
    private AccountHolderService accountHolderService;

    @Test
    void accountHolderCreationThrowsInvalidAgeExceptionIfUnderage(){
        AccountHolderPostRequest request = new AccountHolderPostRequest(
                "Name", "email@email", "lolol",
                "123123", LocalDate.of(2008, 06, 20)
        );

        assertThrows(InvalidAgeException.class,
                () -> accountHolderService.create(request));
    }
}