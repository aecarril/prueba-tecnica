package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CreateMovementCommandHandler implements RequestHandler<CreateMovementCommand, String> {
    private final IMovementRepository movementRepository;

    public CreateMovementCommandHandler(IMovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public String handle(@NotNull CreateMovementCommand command) {
        
        var movement = new Movement();
        movement.setMovementDate(command.getMovementDate());
        movement.setType(command.getType());
        movement.setMovementValue(command.getMovementValue());
        movement.setBalance(command.getBalance());
        movement.setAccountNumber(command.getAccountNumber());

        return this.movementRepository.create(movement);
    }
}
