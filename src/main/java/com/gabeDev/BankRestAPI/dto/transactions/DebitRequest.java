package com.gabeDev.BankRestAPI.dto.transactions;

import com.gabeDev.BankRestAPI.entity.TransactionsType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;


public record DebitRequest (@NotNull TransactionsType type, @NotNull Long senderId,
                           @NotNull BigDecimal amount){
}
