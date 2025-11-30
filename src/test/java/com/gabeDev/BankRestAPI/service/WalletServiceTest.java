package com.gabeDev.BankRestAPI.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.wallet.InsufficientFundsException;
import com.gabeDev.BankRestAPI.exceptions.wallet.InvalidAmountException;
import com.gabeDev.BankRestAPI.exceptions.wallet.WalletNotFoundException;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    private WalletRepo walletRepo;

    @InjectMocks
    private WalletService walletService;

    @Test
    void findByIdThrowsWalletNotFoundExceptionIfNotFound(){
        when(walletRepo.findById(5L)).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () ->
                walletService.findById(5L));
    }

    @Test
    void depositIncreasesBalanceByAmount(){
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(500));
        BigDecimal balance = wallet.getBalance();
        BigDecimal amount = BigDecimal.valueOf(200);

        when(walletRepo.findById(1L)).thenReturn(Optional.of(wallet));
        when(walletRepo.save(wallet)).thenReturn(wallet);

        Wallet updated = walletService.deposit(amount, 1L);

        assertEquals(balance.add(amount), updated.getBalance());

    }

    @Test
    void debitDecreasesBalanceByAmount(){
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(500));
        BigDecimal balance = wallet.getBalance();
        BigDecimal amount = BigDecimal.valueOf(200);

        when(walletRepo.findById(1L)).thenReturn(Optional.of(wallet));
        when(walletRepo.save(wallet)).thenReturn(wallet);

        Wallet updated = walletService.debit(amount, 1L);

        assertEquals(balance.subtract(amount), updated.getBalance());
    }

    @Test
    void negativeOrZeroAmountThrowsInvalidAmountException(){
        Wallet wallet = new Wallet();
        BigDecimal zeroAmount = BigDecimal.valueOf(0);
        BigDecimal negAmount = BigDecimal.valueOf(-1);

        when(walletRepo.findById(1L)).thenReturn(Optional.of(wallet));

        assertThrows(InvalidAmountException.class,
                () -> walletService.debit(zeroAmount, 1L));
        assertThrows(InvalidAmountException.class,
                () -> walletService.deposit(zeroAmount, 1L));

        assertThrows(InvalidAmountException.class,
                () -> walletService.debit(negAmount, 1L));
        assertThrows(InvalidAmountException.class,
                () -> walletService.deposit(negAmount, 1L));
    }

    @Test
    void ifDebitAmountGreaterThanBalanceThrowInsufficientFundsException(){
        Wallet wallet = new Wallet();
        wallet.setTransactionsCount(0L);
        wallet.setBalance(BigDecimal.valueOf(200));
        BigDecimal amount = BigDecimal.valueOf(200.01);

        when(walletRepo.findById(1L)).thenReturn(Optional.of(wallet));

        assertThrows(InsufficientFundsException.class,
                () -> walletService.debit(amount, 1L));

    }





}