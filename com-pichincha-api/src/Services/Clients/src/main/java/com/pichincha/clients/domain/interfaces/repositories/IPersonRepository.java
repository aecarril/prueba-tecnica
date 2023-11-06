package com.pichincha.clients.domain.interfaces.repositories;

import com.pichincha.clients.domain.entities.Person;

import java.util.Optional;

public interface IPersonRepository {
    Optional<Person> getById(String id);
    String create(Person person);
    Person update(Person person);
    void delete(Person person);
}
