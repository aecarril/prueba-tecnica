package com.pichincha.accounts.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountsDomainException extends RuntimeException{
    public AccountsDomainException(String message) {
        super(message);
    }
}
