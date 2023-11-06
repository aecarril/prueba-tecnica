package com.pichincha.accounts.application.queries;

import com.pichincha.accounts.application.dtos.AccountStatusResponse;
import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.entities.Client;
import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.entities.Person;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import com.pichincha.accounts.domain.interfaces.repositories.IClientRepository;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import com.pichincha.accounts.domain.interfaces.repositories.IPersonRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadAccountStatusQueryHandler implements RequestHandler<ReadAccountStatusQuery, List<AccountStatusResponse>> {

    private final IMovementRepository repository;
    private final IClientRepository clientRepository;
    private final IAccountRepository accountRepository;
    private final IPersonRepository personRepository;

    public ReadAccountStatusQueryHandler(IMovementRepository repository,
                                         IClientRepository clientRepository, IAccountRepository accountRepository, IPersonRepository personRepository) {

        this.repository = repository;
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<AccountStatusResponse> handle(@NotNull ReadAccountStatusQuery query) {

        var response = new ArrayList<AccountStatusResponse>();

        //get client
        var client = this.validateClient(query.getClientId());

        //get accounts
        var accounts = this.getAccountsByClientId(query.getClientId());

        for(var account : accounts){
            //get movements
            var movements = this.getMovements(account.getNumber());

            for(var movement : movements){
                var accountStatusResponse = new AccountStatusResponse();
                accountStatusResponse.setStatus(account.getStatus());
                accountStatusResponse.setAccountNumber(account.getNumber());
                accountStatusResponse.setBalance(movement.getBalance());
                accountStatusResponse.setType(movement.getType());
                accountStatusResponse.setMovementDate(movement.getMovementDate());
                accountStatusResponse.setInitialBalance(account.getInitialBalance());
                accountStatusResponse.setClientName(this.getPerson(client.getIdentification()).getNames());
                accountStatusResponse.setMovementValue(movement.getMovementValue());

                response.add(accountStatusResponse);
            }

        }
        return response;
    }

    private Client validateClient(String clientId) {

        return clientRepository.getById(clientId)
                .orElseThrow(() ->
                        new AccountsDomainException("El cliente con id: "
                                + clientId
                                + " no existe"));
    }

    private List<Account> getAccountsByClientId(String clientId) {

        var optAccounts = accountRepository.getByClientId(clientId);

        if (!optAccounts.isPresent()) {
            throw new AccountsDomainException("No existen cuentas para el id: "
                    + clientId);
        }

       return optAccounts.get();
    }

    private List<Movement> getMovements(String accountNumber) {

        var optMovements
                = repository.getMovementsByAccountNumberOrderByDateDesc(accountNumber);

        if (!optMovements.isPresent()) {
            return new ArrayList<>();
        }

        return optMovements.get();
    }

    private Person getPerson(String identification) {

        return personRepository.getByIdentification(identification)
                .orElseThrow(() ->
                        new AccountsDomainException("La persona con identificación : "
                                + identification
                                + " no existe"));
    }
}
