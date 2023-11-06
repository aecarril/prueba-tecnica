package com.pichincha.clients.application.queries;


import com.pichincha.clients.domain.entities.Client;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReadClientQuery implements Request<Client> {
    private String clientId;

    public ReadClientQuery(String clientId) {
        this.clientId = clientId;
    }
}
