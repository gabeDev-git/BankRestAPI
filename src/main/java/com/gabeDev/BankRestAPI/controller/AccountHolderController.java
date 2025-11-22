package com.gabeDev.BankRestAPI.controller;


import com.gabeDev.BankRestAPI.dto.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.dto.AccountHolderResponse;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import com.gabeDev.BankRestAPI.mapper.AccountHolderMapper;
import com.gabeDev.BankRestAPI.service.AccountHolderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountHolderController {

    private final AccountHolderService accountHolderService;

    public AccountHolderController(AccountHolderService accountHolderService){
        this.accountHolderService = accountHolderService;
    }

    @GetMapping
    public ResponseEntity<List<AccountHolderResponse>> getAllHolders(){
        List<AccountHolder> list = accountHolderService.getAllHolders();
        List<AccountHolderResponse> responseList = list.stream()
                .map(AccountHolderMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/create")
    public ResponseEntity<AccountHolderResponse> create(@Valid @RequestBody AccountHolderPostRequest request){
        AccountHolder holder = accountHolderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AccountHolderMapper.toResponse(holder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountHolderResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(AccountHolderMapper.toResponse(accountHolderService.findById(id)));
    }
}
