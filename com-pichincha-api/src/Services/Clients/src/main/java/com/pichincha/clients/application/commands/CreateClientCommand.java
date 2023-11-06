package com.pichincha.clients.application.commands;

import com.pichincha.clients.application.dtos.CreateClientResponse;
import io.jkratz.mediator.core.Request;

public class CreateClientCommand implements Request<CreateClientResponse> {

    private String password;
    private Boolean status;
    private String identification;

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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
