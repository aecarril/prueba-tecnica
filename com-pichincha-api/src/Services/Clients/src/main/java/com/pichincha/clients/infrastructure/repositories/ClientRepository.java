package com.pichincha.clients.infrastructure.repositories;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.domain.interfaces.repositories.IClientRepository;
import com.pichincha.clients.infrastructure.mapper.ClientsMapper;
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
    public Client update(Client client) {

        var clientModel = jpaClientRepository.save(mapper.toClientsModel(client));
        return mapper.toClient(clientModel);
    }

    @Override
    public void delete(Client client) {

        jpaClientRepository.delete(mapper.toClientsModel(client));
    }

    @Override
    public Long getCountByIdentification(String identification) {
        return jpaClientRepository.countByPersonsModelIdentificationAndStatus(identification, Boolean.TRUE);
    }

    @Override
    public Optional<Client> getById(String id) {
        return jpaClientRepository.findById(id)
                .map(clientsModel -> mapper.toClient(clientsModel));
    }

    @Override
    public String create(Client client) {

        var clientModel = jpaClientRepository.save(mapper.toClientsModel(client));

        return clientModel.getId();
    }
}
