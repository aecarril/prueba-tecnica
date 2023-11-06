package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class DeleteAccountCommandHandler implements CommandHandler<DeleteAccountCommand> {

    private final IAccountRepository accountRepository;

    public DeleteAccountCommandHandler(IAccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public void handle(@NotNull DeleteAccountCommand command) {

        var account = this.validateAccount(command.getId());
        accountRepository.delete(account);
    }

    private Account validateAccount(String accountId) {

        return accountRepository.getById(accountId)
                .orElseThrow(()->new AccountsDomainException("La cuenta con id "
                        + accountId
                        + " no existe."));
    }
}
