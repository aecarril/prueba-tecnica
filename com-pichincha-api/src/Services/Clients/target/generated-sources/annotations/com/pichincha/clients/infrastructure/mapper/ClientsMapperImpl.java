package com.pichincha.clients.infrastructure.mapper;

import com.pichincha.clients.domain.entities.Client;
import com.pichincha.clients.infrastructure.models.ClientsModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T23:17:58-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (OpenLogic)"
)
@Component
public class ClientsMapperImpl implements ClientsMapper {

    @Autowired
    private PersonsMapper personsMapper;

    @Override
    public Client toClient(ClientsModel clientModel) {
        if ( clientModel == null ) {
            return null;
        }

        Client client = new Client();

        client.setClientId( clientModel.getId() );
        client.setPassword( clientModel.getPassword() );
        client.setStatus( clientModel.getStatus() );
        client.setPerson( personsMapper.toPerson( clientModel.getPersonsModel() ) );

        return client;
    }

    @Override
    public List<Client> toClients(List<ClientsModel> clientModels) {
        if ( clientModels == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( clientModels.size() );
        for ( ClientsModel clientsModel : clientModels ) {
            list.add( toClient( clientsModel ) );
        }

        return list;
    }

    @Override
    public ClientsModel toClientsModel(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientsModel clientsModel = new ClientsModel();

        clientsModel.setId( client.getClientId() );
        clientsModel.setPassword( client.getPassword() );
        clientsModel.setStatus( client.getStatus() );
        clientsModel.setPersonsModel( personsMapper.toPersonsModel( client.getPerson() ) );

        return clientsModel;
    }
}
