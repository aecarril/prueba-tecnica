package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class DeleteMovementCommandHandler implements CommandHandler<DeleteMovementCommand> {

    private final IMovementRepository movementRepository;

    public DeleteMovementCommandHandler(IMovementRepository movementRepository) {

        this.movementRepository = movementRepository;
    }

    @Override
    public void handle(@NotNull DeleteMovementCommand command) {

        var movement = this.validateMovement(command.getMovementId());
        movementRepository.delete(movement);
    }

    private Movement validateMovement(String movementId) {

        return movementRepository.getById(movementId)
                .orElseThrow(()->new AccountsDomainException("El movimiento con id "
                        + movementId
                        + " no existe."));
    }
}
