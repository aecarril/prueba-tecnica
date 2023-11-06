package com.pichincha.accounts.infrastructure.mapper;

import com.pichincha.accounts.domain.entities.Person;
import com.pichincha.accounts.infrastructure.models.PersonsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonsMapper {

    @Mapping(source = "identification", target = "identification")
    @Mapping(source = "names", target = "names")

    Person toPerson(PersonsModel personsModel);

    List<Person> toPersons(List<PersonsModel> personsModels);

    @InheritInverseConfiguration
    PersonsModel toPersonsModel(Person person);
}