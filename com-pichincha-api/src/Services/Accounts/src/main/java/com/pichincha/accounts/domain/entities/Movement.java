package com.pichincha.accounts.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Movement {

    private String movementId;
    private LocalDateTime movementDate;
    private String type;
    private Double movementValue;
    private Double balance;
    private String accountNumber;
}
