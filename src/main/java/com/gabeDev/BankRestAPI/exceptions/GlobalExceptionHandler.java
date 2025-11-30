package com.gabeDev.BankRestAPI.exceptions;

import com.gabeDev.BankRestAPI.exceptions.account_holder.AccountHolderNotFoundException;
import com.gabeDev.BankRestAPI.exceptions.account_holder.InvalidAgeException;
import com.gabeDev.BankRestAPI.exceptions.transactions.InvalidTransactionsTypeException;
import com.gabeDev.BankRestAPI.exceptions.transactions.SameSenderAndReceiverException;
import com.gabeDev.BankRestAPI.exceptions.transactions.TransactionsNotFoundException;
import com.gabeDev.BankRestAPI.exceptions.wallet.InvalidAmountException;
import com.gabeDev.BankRestAPI.exceptions.wallet.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ExceptionResponse> helper(HttpStatus status, Exception e) {
        return ResponseEntity.status(status)
                .body(new ExceptionResponse(status.value(), e.getMessage()));
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ExceptionResponse> walletNotFound(WalletNotFoundException e){
        return helper(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ExceptionResponse> invalidAge(InvalidAgeException e){
        return helper(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(AccountHolderNotFoundException.class)
    public ResponseEntity<ExceptionResponse> accHolderNotFound(AccountHolderNotFoundException e){
        return helper(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ExceptionResponse> invalidAmount(InvalidAmountException e){
        return helper(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(InvalidTransactionsTypeException.class)
    public ResponseEntity<ExceptionResponse> invalidTransactionsType(InvalidTransactionsTypeException e){
        return helper (HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(SameSenderAndReceiverException.class)
    public ResponseEntity<ExceptionResponse> sameSenderAndReceiver(SameSenderAndReceiverException e){
        return helper (HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(TransactionsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> transactionsNotFound(TransactionsNotFoundException e){
        return helper (HttpStatus.NOT_FOUND, e);
    }

}
