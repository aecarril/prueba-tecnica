package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import com.pichincha.accounts.infrastructure.mapper.AccountsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository implements IAccountRepository {
    @Autowired
    private IJpaAccountsRepository jpaAccountRepository;

    @Autowired
    private AccountsMapper mapper;
    
    @Override
    public Account update(Account account) {

        var accountModel = jpaAccountRepository.save(mapper.toAccountsModel(account));
        return mapper.toAccount(accountModel);
    }

    @Override
    public void delete(Account account) {

        jpaAccountRepository.delete(mapper.toAccountsModel(account));
    }

    @Override
    public Long getCountByAccountNumber(String accountNumber) {
        return jpaAccountRepository.countByNumberAndStatus(accountNumber, "A");
    }

    @Override
    public Optional<Account> getByAccountNumber(String accountNumber) {
        return jpaAccountRepository.findByNumberAndStatus(accountNumber, "A")
                .map(accountsModel -> mapper.toAccount(accountsModel));
    }

    @Override
    public Optional<List<Account>> getByClientId(String clientId) {
        return jpaAccountRepository.findByClientsModelIdAndStatus(clientId, "A")
                .map(accountsModel -> mapper.toAccounts(accountsModel));
    }

    @Override
    public Optional<Account> getById(String id) {
        return jpaAccountRepository.findById(id)
                .map(accountsModel -> mapper.toAccount(accountsModel));
    }

    @Override
    public String create(Account account) {
        var accountModel = jpaAccountRepository.save(mapper.toAccountsModel(account));
        return accountModel.getNumber();
    }
}
