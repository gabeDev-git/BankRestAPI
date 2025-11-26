package com.gabeDev.BankRestAPI.exceptions;

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


}
