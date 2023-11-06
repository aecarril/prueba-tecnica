package com.pichincha.accounts.infrastructure.mapper;

import com.pichincha.accounts.domain.entities.Client;
import com.pichincha.accounts.infrastructure.models.ClientsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientsMapper {

    @Mapping(source = "id", target = "clientId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "personsIdentification", target = "identification")
    Client toClient(ClientsModel clientModel);

    List<Client> toClients(List<ClientsModel> clientModels);

    @InheritInverseConfiguration
    ClientsModel toClientsModel(Client client);
}