package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.domain.entities.Person;
import com.pichincha.accounts.domain.interfaces.repositories.IPersonRepository;
import com.pichincha.accounts.infrastructure.mapper.PersonsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PersonRepository implements IPersonRepository {
    @Autowired
    private IJpaPersonsRepository jpaPersonsRepository;

    @Autowired
    private PersonsMapper mapper;


    @Override
    public Optional<Person> getByIdentification(String identification) {
        return jpaPersonsRepository.findByIdentification(identification)
                .map(personsModel ->  mapper.toPerson(personsModel));
    }
}
