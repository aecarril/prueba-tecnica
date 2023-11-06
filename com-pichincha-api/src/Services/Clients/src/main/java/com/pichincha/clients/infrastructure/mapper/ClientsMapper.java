package com.pichincha.clients.infrastructure.mapper;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.infrastructure.models.ClientsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = { PersonsMapper.class })
public interface ClientsMapper {

    @Mapping(source = "id", target = "clientId")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "personsModel", target = "person")
    Client toClient(ClientsModel clientModel);

    List<Client> toClients(List<ClientsModel> clientModels);

    @InheritInverseConfiguration
    ClientsModel toClientsModel(Client client);
}