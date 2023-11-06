package com.pichincha.accounts.application.commands;

import io.jkratz.mediator.core.Command;

public class DepositCommand implements Command {
    private String accountNumber;
    private Double money;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
