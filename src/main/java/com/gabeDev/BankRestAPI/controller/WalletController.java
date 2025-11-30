package com.gabeDev.BankRestAPI.controller;

import com.gabeDev.BankRestAPI.dto.wallet.WalletResponse;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.mapper.WalletMapper;
import com.gabeDev.BankRestAPI.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("api/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping()
    public ResponseEntity<List<WalletResponse>> findAll(){
        List<Wallet> walletList = walletService.findAll();
        List<WalletResponse> responseList = walletList.stream()
                .map(WalletMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public WalletResponse findById(@PathVariable Long id){
        return WalletMapper.toResponse(walletService.findById(id));
    }
}
