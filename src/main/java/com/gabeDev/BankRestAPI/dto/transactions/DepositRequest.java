package com.gabeDev.BankRestAPI.dto.transactions;

import com.gabeDev.BankRestAPI.entity.TransactionsType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DepositRequest(@NotNull TransactionsType type, @NotNull Long receiverId,
                             @NotNull BigDecimal amount) {
}
