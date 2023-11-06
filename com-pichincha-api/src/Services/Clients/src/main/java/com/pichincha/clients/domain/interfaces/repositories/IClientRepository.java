package com.pichincha.clients.domain.interfaces.repositories;

import com.pichincha.clients.domain.entities.Client;

import java.util.Optional;

public interface IClientRepository {
    Optional<Client> getById(String id);
    String create(Client client);
    Client update(Client client);
    void delete(Client client);
    Long getCountByIdentification(String identification);
}
