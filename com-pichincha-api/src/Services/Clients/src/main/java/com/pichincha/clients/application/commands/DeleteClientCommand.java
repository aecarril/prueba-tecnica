package com.pichincha.clients.application.commands;

import io.jkratz.mediator.core.Command;

public class DeleteClientCommand implements Command {
    private String clientId;

    public DeleteClientCommand(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }
}
