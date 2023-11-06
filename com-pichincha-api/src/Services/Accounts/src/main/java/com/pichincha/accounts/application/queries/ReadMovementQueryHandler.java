package com.pichincha.accounts.application.queries;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ReadMovementQueryHandler implements RequestHandler<ReadMovementQuery, Movement> {

    private final IMovementRepository repository;

    public ReadMovementQueryHandler(IMovementRepository repository) {

        this.repository = repository;
    }

    @Override
    public Movement handle(@NotNull ReadMovementQuery readMovementQuery) {

        return repository.getById(readMovementQuery.getMovementId())
                .orElseThrow(() ->
                        new AccountsDomainException("El movimiento con id "
                                + readMovementQuery.getMovementId()
                                + " no existe."));
    }
}
