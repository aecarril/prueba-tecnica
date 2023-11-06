package com.pichincha.clients.infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "CLIENTS")
@Setter
@Getter
public class ClientsModel {
    @Id
    private String id;

    private String password;

    private Boolean status;

    @OneToOne
    @JoinColumn(name = "persons_identification", updatable = false)
    private PersonsModel personsModel;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
        this.status = Boolean.TRUE;
    }
}