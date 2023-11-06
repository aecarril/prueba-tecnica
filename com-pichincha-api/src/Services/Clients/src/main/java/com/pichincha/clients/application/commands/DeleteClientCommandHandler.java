package com.pichincha.clients.application.commands;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.domain.exceptions.ClientsDomainException;
import com.pichincha.clients.domain.interfaces.repositories.IClientRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class DeleteClientCommandHandler implements CommandHandler<DeleteClientCommand> {

    private final IClientRepository clientRepository;

    public DeleteClientCommandHandler(IClientRepository clientRepository) {

        this.clientRepository = clientRepository;
    }

    @Override
    public void handle(@NotNull DeleteClientCommand command) {

        var client = this.validateClient(command.getClientId());
        clientRepository.delete(client);
    }

    private Client validateClient(String clientId) {

        return clientRepository.getById(clientId)
                .orElseThrow(()->new ClientsDomainException("El cliente con id "
                        + clientId
                        + " no existe."));
    }
}
