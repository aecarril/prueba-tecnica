package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import com.pichincha.accounts.infrastructure.mapper.MovementsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class MovementRepository implements IMovementRepository {
    @Autowired
    private IJpaMovementsRepository jpaMovementRepository;

    @Autowired
    private MovementsMapper mapper;
    
    @Override
    public Movement update(Movement movement) {

        var movementModel = jpaMovementRepository.save(mapper.toMovementsModel(movement));
        return mapper.toMovement(movementModel);
    }

    @Override
    public void delete(Movement movement) {

        jpaMovementRepository.delete(mapper.toMovementsModel(movement));
    }

    @Override
    public Optional<Movement> getLastMovementByAccountNumber(String accountNumber) {
        return jpaMovementRepository.findFirstByAccountNumberOrderByMovementsDateDesc(accountNumber)
                .map(movementsModel -> mapper.toMovement(movementsModel));
    }

    @Override
    public Optional<List<Movement>> getMovementsByAccountNumberOrderByDateDesc(String accountNumber) {
        return jpaMovementRepository.findByAccountNumberOrderByMovementsDateDesc(accountNumber)
                .map(movementsModel -> mapper.toMovements(movementsModel));
    }

    @Override
    public Optional<Movement> getById(String id) {
        return jpaMovementRepository.findById(id)
                .map(movementsModel -> mapper.toMovement(movementsModel));
    }

    @Override
    public String create(Movement movement) {
        var movementModel = jpaMovementRepository.save(mapper.toMovementsModel(movement));
        return movementModel.getId();
    }
}
