package com.gabeDev.BankRestAPI.dto.transactions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabeDev.BankRestAPI.entity.TransactionsType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DepositResponse(Long transactionId, Long receiverWalletId,
                              String receiverName, BigDecimal amount,
                              @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
                              TransactionsType type) implements TransactionsResponse {}
