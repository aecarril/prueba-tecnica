package com.pichincha.accounts.infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTS")
@Setter
@Getter
public class ClientsModel {
    @Id
    private String id;

    private Boolean status;

    @Column(name = "persons_identification")
    private String personsIdentification;
}