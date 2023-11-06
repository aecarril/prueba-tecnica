package com.pichincha.accounts.application.queries;


import com.pichincha.accounts.domain.entities.Movement;
import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReadMovementQuery implements Request<Movement> {
    private String movementId;

    public ReadMovementQuery(String movementId) {
        this.movementId = movementId;
    }
}
