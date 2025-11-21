package com.gabeDev.BankRestAPI.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AccountHolderResponse(Long holderId, Long walletId, String fullName, String email,
                                    LocalDate birthDate, LocalDateTime createdAt,
                                    LocalDateTime updatedAt) {
}
