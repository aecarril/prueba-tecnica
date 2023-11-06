package com.pichincha.clients.application.commands;

import com.pichincha.clients.application.dtos.CreateClientResponse;
import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.domain.entities.Person;
import com.pichincha.clients.domain.exceptions.ClientsDomainException;
import com.pichincha.clients.domain.interfaces.repositories.IClientRepository;
import com.pichincha.clients.domain.interfaces.repositories.IPersonRepository;
import io.jkratz.mediator.core.RequestHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class CreateClientCommandHandler implements RequestHandler<CreateClientCommand, CreateClientResponse> {
    private final IPersonRepository personRepository;
    private final IClientRepository clientRepository;

    public CreateClientCommandHandler(IPersonRepository personRepository,
                                      IClientRepository clientRepository) {
        this.personRepository = personRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public CreateClientResponse handle(@NotNull CreateClientCommand command) {
        
        var client = new Client();
        client.setPassword(command.getPassword());

        client.setPerson(this.validatePerson(command.getIdentification()));

        this.validateIdentification(command.getIdentification());

        return new CreateClientResponse(this.clientRepository.create(client));
    }

    private Person validatePerson(String identification){
        var person = personRepository.getById(identification);

        if (!person.isPresent()) {
            throw new ClientsDomainException("La persona con esa identificación no existe");
        }

        return person.get();
    }

    private void validateIdentification(String identification){
        if (clientRepository.getCountByIdentification(identification) > 0){
            throw new ClientsDomainException("Ya existe un cliente con ese número de cédula");
        }
    }
}
