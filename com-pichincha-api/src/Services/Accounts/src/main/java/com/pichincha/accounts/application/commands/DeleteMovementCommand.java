package com.pichincha.accounts.application.commands;

import io.jkratz.mediator.core.Command;

public class DeleteMovementCommand implements Command {
    private String movementId;

    public DeleteMovementCommand(String movementId) {
        this.movementId = movementId;
    }

    public String getMovementId() {
        return movementId;
    }
}
