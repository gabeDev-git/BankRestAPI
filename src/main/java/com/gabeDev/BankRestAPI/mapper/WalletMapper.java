package com.gabeDev.BankRestAPI.mapper;

import com.gabeDev.BankRestAPI.dto.WalletResponse;
import com.gabeDev.BankRestAPI.entity.Wallet;

public class WalletMapper {

    public static WalletResponse toResponse(Wallet wallet){
        return new WalletResponse(wallet.getId(), wallet.getHolder().getId(), wallet.getBalance(),
                wallet.getTransactionsCount(), wallet.getCreatedAt(), wallet.getUpdatedAt());
    }
}
