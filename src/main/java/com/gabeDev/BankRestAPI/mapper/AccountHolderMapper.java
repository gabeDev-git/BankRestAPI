package com.gabeDev.BankRestAPI.mapper;


import com.gabeDev.BankRestAPI.dto.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import java.time.LocalDateTime;

public class AccountHolderMapper {

    public static AccountHolder toEntity(AccountHolderPostRequest request){
        AccountHolder holder = new AccountHolder();
        holder.setFullName(request.fullName());
        holder.setEmail(request.email());
        holder.setBirthDate(request.birthDate());
        holder.setDocument(request.document());
        holder.setPassword(request.password());

        return holder;
    }
}
