package com.gabeDev.BankRestAPI.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AccountHolderPostRequest(@NotBlank @Size(max = 160) String fullName,
                                       @NotBlank @Email String email,
                                       @NotBlank @Size(max = 40) String password,
                                       @NotBlank @Size(max = 20) String document,
                                       @NotNull LocalDate birthDate) {
}
