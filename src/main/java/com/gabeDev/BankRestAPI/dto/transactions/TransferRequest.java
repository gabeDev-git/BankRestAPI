package com.gabeDev.BankRestAPI.dto.transactions;


import com.gabeDev.BankRestAPI.entity.TransactionsType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferRequest(@NotNull TransactionsType type, @NotNull Long senderId,
                              @NotNull Long receiverId, @NotNull BigDecimal amount) {
}
