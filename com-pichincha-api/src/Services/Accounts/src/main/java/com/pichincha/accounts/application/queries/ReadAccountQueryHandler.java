package com.pichincha.accounts.application.queries;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ReadAccountQueryHandler implements RequestHandler<ReadAccountQuery, Account> {

    private final IAccountRepository repository;

    public ReadAccountQueryHandler(IAccountRepository repository) {

        this.repository = repository;
    }

    @Override
    public Account handle(@NotNull ReadAccountQuery readAccountQuery) {

        return repository.getById(readAccountQuery.getAccountNumber())
                .orElseThrow(() ->
                        new AccountsDomainException("El número de cuenta "
                                + readAccountQuery.getAccountNumber()
                                + " no existe."));
    }
}
