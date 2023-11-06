package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.domain.entities.Client;
import com.pichincha.accounts.domain.interfaces.repositories.IClientRepository;
import com.pichincha.accounts.infrastructure.mapper.ClientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepository implements IClientRepository {
    @Autowired
    private IJpaClientsRepository jpaClientRepository;

    @Autowired
    private ClientsMapper mapper;

    @Override
    public Optional<Client> getById(String id) {
        return jpaClientRepository.findById(id)
                .map(clientsModel -> mapper.toClient(clientsModel));
    }
}
