package com.pichincha.accounts.infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "MOVEMENTS")
@Setter
@Getter
public class MovementsModel {
    @Id
    private String id;

    @Column(name = "movements_date")
    private LocalDateTime movementsDate;

    private String type;

    @Column(name = "movements_value")
    private Double movementsValue;

    private Double balance;

    @Column(name = "account_number")
    private String accountNumber;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
        this.movementsDate = LocalDateTime.now();
    }
}