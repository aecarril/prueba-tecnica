package com.pichincha.clients.infrastructure.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONS")
@Setter
@Getter
public class PersonsModel {
    @Id
    private String identification;

    private String names;

    private String gender;

    private Integer age;

    private String address;

    private String phone;
}