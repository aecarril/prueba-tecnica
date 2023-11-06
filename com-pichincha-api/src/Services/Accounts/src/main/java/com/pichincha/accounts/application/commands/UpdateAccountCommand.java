package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Account;
import io.jkratz.mediator.core.Request;

public class UpdateAccountCommand implements Request<Account> {
    private String id;
    private Double initialBalance;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
