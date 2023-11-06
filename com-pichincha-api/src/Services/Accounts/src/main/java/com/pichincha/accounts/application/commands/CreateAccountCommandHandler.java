package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.entities.Client;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import com.pichincha.accounts.domain.interfaces.repositories.IClientRepository;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CreateAccountCommandHandler implements RequestHandler<CreateAccountCommand, String> {
    private final IAccountRepository accountRepository;
    private final IClientRepository clientRepository;

    public CreateAccountCommandHandler(IAccountRepository accountRepository,
                                       IClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public String handle(@NotNull CreateAccountCommand command) {

        var client = this.validateClient(command.getClientId());

        this.validateAccountNumber(command.getNumber());

        var account = new Account();
        account.setNumber(command.getNumber());
        account.setStatus("A");
        account.setInitialBalance(command.getInitialBalance());
        account.setType(command.getType());
        account.setClient(client);

        return this.accountRepository.create(account);
    }

    private Client validateClient(String clientId) {

        return clientRepository.getById(clientId)
                .orElseThrow(() ->
                        new AccountsDomainException("El cliente con id: "
                                + clientId
                                + " no existe"));
    }

    private void validateAccountNumber(String accountNumber){
        if (accountRepository.getCountByAccountNumber(accountNumber) > 0) {
            throw new AccountsDomainException("La cuenta ya existe: ");
        }
    }
}
