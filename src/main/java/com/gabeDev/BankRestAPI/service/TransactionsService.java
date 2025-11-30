package com.gabeDev.BankRestAPI.service;

import com.gabeDev.BankRestAPI.dto.transactions.DebitRequest;
import com.gabeDev.BankRestAPI.dto.transactions.DepositRequest;
import com.gabeDev.BankRestAPI.dto.transactions.TransferRequest;
import com.gabeDev.BankRestAPI.entity.Transactions;
import com.gabeDev.BankRestAPI.entity.TransactionsType;
import com.gabeDev.BankRestAPI.entity.Wallet;
import com.gabeDev.BankRestAPI.exceptions.transactions.InvalidTransactionsTypeException;
import com.gabeDev.BankRestAPI.exceptions.transactions.SameSenderAndReceiverException;
import com.gabeDev.BankRestAPI.exceptions.transactions.TransactionsNotFoundException;
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
            throw new InvalidTransactionsTypeException(TransactionsType.DEPOSIT);
        }

        Wallet receiverWallet = walletService.deposit(request.amount(), request.receiverId());

        return transactionsRepo.save(TransactionsMapper.toDeposit(request, receiverWallet));
    }

    @Transactional
    public Transactions debit(DebitRequest request){
        if(request.type() != TransactionsType.DEBIT){
            throw new InvalidTransactionsTypeException(TransactionsType.DEBIT);
        }

        Wallet senderWallet = walletService.debit(request.amount(), request.senderId());
        return transactionsRepo.save(TransactionsMapper.toDebit(request, senderWallet));
    }

    @Transactional
    public Transactions transfer(TransferRequest request){
        if(request.type() != TransactionsType.TRANSFER){
            throw new InvalidTransactionsTypeException(TransactionsType.TRANSFER);
        }
        if(request.receiverId().equals(request.senderId())){
            throw new SameSenderAndReceiverException(request.senderId(), request.receiverId());
        }

        Wallet receiverWallet = walletService.deposit(request.amount(), request.receiverId());
        Wallet senderWallet = walletService.debit(request.amount(), request.senderId());

        return transactionsRepo.save(TransactionsMapper.toTransfer(request, senderWallet, receiverWallet));
    }

    public Transactions findById(Long id){
        return transactionsRepo.findById(id)
                .orElseThrow(() -> new TransactionsNotFoundException(id));
    }

    public List<Transactions> findAll(){
        return transactionsRepo.findAll();
    }
}
