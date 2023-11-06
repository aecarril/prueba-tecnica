package com.pichincha.accounts.domain.interfaces.repositories;

import com.pichincha.accounts.domain.entities.Person;

import java.util.Optional;

public interface IPersonRepository {
    Optional<Person> getByIdentification(String identification);
}
