package com.gabeDev.BankRestAPI.controller;
import com.gabeDev.BankRestAPI.dto.transactions.*;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.mapper.TransactionsMapper;
import com.gabeDev.BankRestAPI.service.TransactionsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;

    public TransactionsController(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionsResponse>> findAll(){
        List<Transactions> transactionsList = transactionsService.findAll();
        List<TransactionsResponse> responseList = transactionsList
                .stream().map(TransactionsMapper::toGenericResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsResponse> findById(@PathVariable Long id){
        Transactions transactions = transactionsService.findById(id);
       return ResponseEntity.ok(TransactionsMapper.toGenericResponse(transactions));
    }

    @PostMapping("/debit")
    public ResponseEntity<DebitResponse> debit(@Valid @RequestBody DebitRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionsMapper.toDebitResponse(transactionsService.debit(request)));
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@Valid @RequestBody DepositRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionsMapper.toDepositResponse(transactionsService.deposit(request)));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@Valid @RequestBody TransferRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionsMapper.toTransferResponse(transactionsService.transfer(request)));
    }
}
