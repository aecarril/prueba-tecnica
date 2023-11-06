package com.pichincha.clients.application.commands;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.domain.exceptions.ClientsDomainException;
import com.pichincha.clients.domain.interfaces.repositories.IClientRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class UpdateClientCommandHandler implements RequestHandler<UpdateClientCommand, Client> {

    private final IClientRepository clientRepository;

    public UpdateClientCommandHandler(IClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public Client handle(@NotNull UpdateClientCommand command) {

        var client = this.validateClient(command.getClientId());

        client.setPassword(command.getPassword());
        client.setStatus(command.getStatus());
        
        return clientRepository.update(client);
    }

    private Client validateClient(String clientId) {

        return clientRepository.getById(clientId)
                .orElseThrow(() ->
                        new ClientsDomainException("El cliente con id: "
                                + clientId
                                + " no existe"));
    }
}