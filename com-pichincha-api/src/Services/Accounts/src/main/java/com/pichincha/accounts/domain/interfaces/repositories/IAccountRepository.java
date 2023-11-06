package com.pichincha.accounts.domain.interfaces.repositories;

import com.pichincha.accounts.domain.entities.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository {
    Optional<Account> getById(String id);
    String create(Account account);
    Account update(Account account);
    void delete(Account account);
    Long getCountByAccountNumber(String accountNumber);
    Optional<Account> getByAccountNumber(String accountNumber);
    Optional<List<Account>> getByClientId(String clientId);
}
