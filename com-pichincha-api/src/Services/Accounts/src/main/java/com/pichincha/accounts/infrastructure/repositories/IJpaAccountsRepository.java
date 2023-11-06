package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.infrastructure.models.AccountsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IJpaAccountsRepository extends JpaRepository<AccountsModel, String> {
    Long countByNumberAndStatus(String accountNumber, String status);
    Optional<AccountsModel> findByNumberAndStatus(String accountNumber, String status);
    Optional<List<AccountsModel>> findByClientsModelIdAndStatus(String clientId, String status);
}
