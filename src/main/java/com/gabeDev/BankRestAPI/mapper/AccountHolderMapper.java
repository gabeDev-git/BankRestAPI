package com.gabeDev.BankRestAPI.mapper;


import com.gabeDev.BankRestAPI.dto.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.dto.AccountHolderResponse;
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
        holder.setCreatedAt(LocalDateTime.now());
        holder.setUpdatedAt(LocalDateTime.now());

        return holder;
    }

    public static AccountHolderResponse toResponse(AccountHolder holder){
        return new AccountHolderResponse(holder.getId(), holder.getWallet().getId(),
                holder.getFullName(), holder.getEmail(),
                holder.getCreatedAt(), holder.getUpdatedAt());
    }
}
