package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

@Component
public class UpdateAccountCommandHandler implements RequestHandler<UpdateAccountCommand, Account> {

    private final IAccountRepository accountRepository;

    public UpdateAccountCommandHandler(IAccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public Account handle(@NotNull UpdateAccountCommand command) {

        var account = this.validateAccount(command.getId());

        account.setInitialBalance(command.getInitialBalance());
        account.setStatus(command.getStatus());

        return accountRepository.update(account);
    }

    private Account validateAccount(String accountId) {

        return accountRepository.getById(accountId)
                .orElseThrow(() ->
                        new AccountsDomainException("La cuenta con id: "
                                + accountId
                                + " no existe"));
    }
}