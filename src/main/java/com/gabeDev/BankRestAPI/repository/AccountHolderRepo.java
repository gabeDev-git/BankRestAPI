package com.gabeDev.BankRestAPI.repository;

import com.gabeDev.BankRestAPI.entity.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {
}
