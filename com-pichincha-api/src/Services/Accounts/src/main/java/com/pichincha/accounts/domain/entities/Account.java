package com.pichincha.accounts.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {

    private String number;
    private String type;
    private Double initialBalance;
    private String status;
    private Client client;
}
