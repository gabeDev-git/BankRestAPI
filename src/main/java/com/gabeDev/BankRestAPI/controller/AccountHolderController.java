package com.gabeDev.BankRestAPI.controller;


import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderPostRequest;
import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderPutRequest;
import com.gabeDev.BankRestAPI.dto.account_holder.AccountHolderResponse;
import com.gabeDev.BankRestAPI.entity.AccountHolder;
import com.gabeDev.BankRestAPI.mapper.AccountHolderMapper;
import com.gabeDev.BankRestAPI.service.AccountHolderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
                .toList();

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

    @PutMapping("/{id}")
    public ResponseEntity<AccountHolderResponse> update(@PathVariable Long id, @RequestBody
                                                        AccountHolderPutRequest request){
        return ResponseEntity.ok(AccountHolderMapper.toResponse(
                accountHolderService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        accountHolderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
