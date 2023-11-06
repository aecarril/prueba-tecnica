package com.pichincha.clients.infrastructure.mapper;

import com.pichincha.clients.domain.entities.Person;
import com.pichincha.clients.infrastructure.models.PersonsModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-04T23:17:58-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.16 (OpenLogic)"
)
@Component
public class PersonsMapperImpl implements PersonsMapper {

    @Override
    public Person toPerson(PersonsModel personsModel) {
        if ( personsModel == null ) {
            return null;
        }

        Person person = new Person();

        person.setIdentification( personsModel.getIdentification() );
        person.setNames( personsModel.getNames() );
        person.setGender( personsModel.getGender() );
        person.setAge( personsModel.getAge() );
        person.setAddress( personsModel.getAddress() );
        person.setPhone( personsModel.getPhone() );

        return person;
    }

    @Override
    public List<Person> toPersons(List<PersonsModel> personsModels) {
        if ( personsModels == null ) {
            return null;
        }

        List<Person> list = new ArrayList<Person>( personsModels.size() );
        for ( PersonsModel personsModel : personsModels ) {
            list.add( toPerson( personsModel ) );
        }

        return list;
    }

    @Override
    public PersonsModel toPersonsModel(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonsModel personsModel = new PersonsModel();

        personsModel.setIdentification( person.getIdentification() );
        personsModel.setNames( person.getNames() );
        personsModel.setGender( person.getGender() );
        personsModel.setAge( person.getAge() );
        personsModel.setAddress( person.getAddress() );
        personsModel.setPhone( person.getPhone() );

        return personsModel;
    }
}
