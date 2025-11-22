package com.gabeDev.BankRestAPI.dto;


import com.gabeDev.BankRestAPI.entity.TransactionsType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionsPostRequest(@NotNull TransactionsType type, Long senderId,
                                      Long receiverId, @NotNull BigDecimal amount) {
}
