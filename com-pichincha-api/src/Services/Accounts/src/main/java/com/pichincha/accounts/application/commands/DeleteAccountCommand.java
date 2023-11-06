package com.pichincha.accounts.application.commands;

import io.jkratz.mediator.core.Command;

public class DeleteAccountCommand implements Command {
    private String id;

    public DeleteAccountCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
