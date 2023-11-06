package com.pichincha.clients.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {

    private String identification;
    private String names;
    private String gender;
    private Integer age;
    private String address;
    private String phone;
}
