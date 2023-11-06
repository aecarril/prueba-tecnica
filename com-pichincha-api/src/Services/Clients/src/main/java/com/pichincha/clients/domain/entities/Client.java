package com.pichincha.clients.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client{

    private String clientId;
    private String password;
    private Boolean status;
    private Person person;
}
