package com.gabeDev.BankRestAPI.dto.account_holder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AccountHolderPutRequest(@Size(max = 160) String fullName,
                                      @Size(max = 40) String password,
                                      @Email String email) {
}
