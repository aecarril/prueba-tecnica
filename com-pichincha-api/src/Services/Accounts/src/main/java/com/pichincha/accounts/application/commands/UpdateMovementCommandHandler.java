package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UpdateMovementCommandHandler implements RequestHandler<UpdateMovementCommand, Movement> {

    private final IMovementRepository movementRepository;

    public UpdateMovementCommandHandler(IMovementRepository movementRepository) {

        this.movementRepository = movementRepository;
    }

    @Override
    public Movement handle(@NotNull UpdateMovementCommand command) {

        var movement = this.validateMovement(command.getMovementId());

        movement.setMovementDate(command.getMovementDate());
        movement.setType(command.getType());
        movement.setMovementValue(command.getMovementValue());
        movement.setBalance(command.getBalance());
        movement.setAccountNumber(command.getAccountNumber());

        return movementRepository.update(movement);
    }

    private Movement validateMovement(String movementId) {

        return movementRepository.getById(movementId)
                .orElseThrow(() ->
                        new AccountsDomainException("El movimiento con id: "
                                + movementId
                                + " no existe"));
    }
}