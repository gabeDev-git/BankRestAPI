package com.gabeDev.BankRestAPI.dto.account_holder;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record AccountHolderResponse(Long holderId, Long walletId, String fullName, String email,
                                    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
                                    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime updatedAt) {
}
