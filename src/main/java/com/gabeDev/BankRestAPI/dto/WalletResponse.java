package com.gabeDev.BankRestAPI.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WalletResponse(Long walletId, Long ownerId, BigDecimal balance,
Long transactionsCount, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")LocalDateTime createdAt,
                             @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime updatedAt) {
}
