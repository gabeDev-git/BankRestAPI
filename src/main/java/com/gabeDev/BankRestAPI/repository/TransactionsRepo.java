package com.gabeDev.BankRestAPI.repository;

import com.gabeDev.BankRestAPI.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
}
