package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.infrastructure.models.MovementsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface IJpaMovementsRepository extends JpaRepository<MovementsModel, String> {
    Optional<MovementsModel> findFirstByAccountNumberOrderByMovementsDateDesc(String accountNumber);
    Optional<List<MovementsModel>> findByAccountNumberOrderByMovementsDateDesc(String accountNumber);
}
