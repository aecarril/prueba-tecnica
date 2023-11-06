package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Movement;
import io.jkratz.mediator.core.Request;

import java.time.LocalDateTime;

public class UpdateMovementCommand implements Request<Movement> {
    private String movementId;
    private LocalDateTime movementDate;
    private String type;
    private Double movementValue;
    private Double balance;
    private String accountNumber;

    public String getMovementId() {
        return movementId;
    }

    public void setMovementId(String movementId) {
        this.movementId = movementId;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMovementValue() {
        return movementValue;
    }

    public void setMovementValue(Double movementValue) {
        this.movementValue = movementValue;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
