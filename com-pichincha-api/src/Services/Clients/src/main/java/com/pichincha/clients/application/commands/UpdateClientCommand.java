package com.pichincha.clients.application.commands;

import com.pichincha.clients.domain.entities.Client;
import io.jkratz.mediator.core.Request;

public class UpdateClientCommand implements Request<Client> {
    private String clientId;
    private String password;
    private Boolean status;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
