package com.pichincha.accounts.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {

    private String clientId;
    private Boolean status;
    private String identification;
}
