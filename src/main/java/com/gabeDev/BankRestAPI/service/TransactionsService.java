package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.transactions.DebitRequest;
import com.gabeDev.BankRestAPI.dto.transactions.DepositRequest;
import com.gabeDev.BankRestAPI.dto.transactions.TransferRequest;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.entity.TransactionsType;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.mapper.TransactionsMapper;
import com.gabeDev.BankRestAPI.repository.TransactionsRepo;
import com.gabeDev.BankRestAPI.repository.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    private final WalletService walletService;
    private final TransactionsRepo transactionsRepo;
    private final WalletRepo walletRepo;

    public TransactionsService(WalletService walletService, TransactionsRepo transactionsRepo,
                               WalletRepo walletRepo){
        this.walletService = walletService;
        this.transactionsRepo = transactionsRepo;
        this.walletRepo = walletRepo;
    }

    @Transactional
    public Transactions deposit(DepositRequest request){
        if(request.type() != TransactionsType.DEPOSIT){
            throw new RuntimeException(("Transaction type is not DEPOSIT"));
        }

        Wallet receiverWallet = walletService.deposit(request.amount(), request.receiverId());

        return transactionsRepo.save(TransactionsMapper.toDeposit(request, receiverWallet));
    }

    @Transactional
    public Transactions debit(DebitRequest request){
        if(request.type() != TransactionsType.DEBIT){
            throw new RuntimeException(("Transaction type is not DEBIT"));
        }

        Wallet senderWallet = walletService.debit(request.amount(), request.senderId());
        return transactionsRepo.save(TransactionsMapper.toDebit(request, senderWallet));
    }

    @Transactional
    public Transactions transfer(TransferRequest request){
        if(request.type() != TransactionsType.TRANSFER){
            throw new RuntimeException(("Transactions type is not TRANSFER"));
        }
        if(request.receiverId().equals(request.senderId())){
            throw new RuntimeException(("Sender and receiver cannot be the same!"));
        }

        Wallet receiverWallet = walletService.deposit(request.amount(), request.receiverId());
        Wallet senderWallet = walletService.debit(request.amount(), request.senderId());

        return transactionsRepo.save(TransactionsMapper.toTransfer(request, senderWallet, receiverWallet));
    }

    public Transactions findById(Long id){
        return transactionsRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public List<Transactions> findAll(){
        return transactionsRepo.findAll();
    }
}
