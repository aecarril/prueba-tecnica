package com.pichincha.clients.infrastructure.repositories;

import com.pichincha.clients.domain.entities.Person;
import com.pichincha.clients.domain.interfaces.repositories.IPersonRepository;
import com.pichincha.clients.infrastructure.mapper.PersonsMapper;
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
    public Person update(Person person) {

        var personsModel = jpaPersonsRepository.save(mapper.toPersonsModel(person));
        return mapper.toPerson(personsModel);
    }

    @Override
    public void delete(Person person) {

        jpaPersonsRepository.delete(mapper.toPersonsModel(person));
    }

    @Override
    public Optional<Person> getById(String id) {
        return jpaPersonsRepository.findById(id)
                .map(accountsModel -> mapper.toPerson(accountsModel));
    }

    @Override
    public String create(Person person) {
        var personsModel = jpaPersonsRepository.save(mapper.toPersonsModel(person));
        return personsModel.getIdentification();
    }
}
