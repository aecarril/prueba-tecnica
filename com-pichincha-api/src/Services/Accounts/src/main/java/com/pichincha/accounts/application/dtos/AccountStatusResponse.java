package com.pichincha.accounts.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AccountStatusResponse {

    private LocalDateTime movementDate;
    private String clientName;
    private String accountNumber;
    private String type;
    private Double initialBalance;
    private String status;
    private Double movementValue;
    private Double balance;
}
