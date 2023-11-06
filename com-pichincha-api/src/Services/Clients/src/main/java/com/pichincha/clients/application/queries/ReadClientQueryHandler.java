package com.pichincha.clients.application.queries;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.domain.exceptions.ClientsDomainException;
import com.pichincha.clients.domain.interfaces.repositories.IClientRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ReadClientQueryHandler implements RequestHandler<ReadClientQuery, Client> {

    private final IClientRepository repository;

    public ReadClientQueryHandler(IClientRepository repository) {

        this.repository = repository;
    }

    @Override
    public Client handle(@NotNull ReadClientQuery readClientQuery) {

        return repository.getById(readClientQuery.getClientId())
                .orElseThrow(() ->
                        new ClientsDomainException("El cliente "
                                + readClientQuery.getClientId()
                                + " no existe."));
    }
}
