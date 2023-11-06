package com.pichincha.accounts.domain.interfaces.repositories;

import com.pichincha.accounts.domain.entities.Movement;

import java.util.Optional;
import java.util.List;

public interface IMovementRepository {
    Optional<Movement> getById(String id);
    String create(Movement movement);
    Movement update(Movement movement);
    void delete(Movement movement);
    Optional<Movement> getLastMovementByAccountNumber(String accountNumber);
    Optional<List<Movement>> getMovementsByAccountNumberOrderByDateDesc(String accountNumber);
}
