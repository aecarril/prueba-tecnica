package com.pichincha.clients.application.dtos;

import lombok.Getter;

@Getter
public class CreateClientResponse {
    private String id;

    public CreateClientResponse(String id) {
        this.id = id;
    }
}
