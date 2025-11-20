package com.gabeDev.BankRestAPI.repository;

import com.gabeDev.BankRestAPI.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
}
