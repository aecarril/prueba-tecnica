package com.pichincha.accounts.application.queries;


import com.pichincha.accounts.domain.entities.Account;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReadAccountQuery implements Request<Account> {
    private String accountNumber;

    public ReadAccountQuery(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
