package com.pichincha.accounts.infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
@Setter
@Getter
public class AccountsModel {
    @Id
    @Column(name = "account_number")
    private String number;

    private String type;

    @Column(name = "initial_balance")
    private Double initialBalance;

    private String status;

    @OneToOne
    @JoinColumn(name = "clients_id", updatable = false)
    private ClientsModel clientsModel;
}