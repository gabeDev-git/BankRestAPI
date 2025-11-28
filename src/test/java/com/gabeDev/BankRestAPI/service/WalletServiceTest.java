package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.repository.WalletRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class WalletServiceTest {

    @Autowired
    private WalletRepo walletRepo;
    @Autowired
    private WalletService walletService;

    @Test
    void depositIncreasesBalanceByAmountAndSavesWallet(){

    }




}