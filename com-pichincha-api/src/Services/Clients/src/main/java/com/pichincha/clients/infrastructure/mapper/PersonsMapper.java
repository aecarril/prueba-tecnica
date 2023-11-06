package com.pichincha.clients.infrastructure.mapper;

import com.pichincha.clients.domain.entities.Person;
import com.pichincha.clients.infrastructure.models.PersonsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonsMapper {

    @Mapping(source = "identification", target = "identification")
    @Mapping(source = "names", target = "names")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    Person toPerson(PersonsModel personsModel);

    List<Person> toPersons(List<PersonsModel> personsModels);

    @InheritInverseConfiguration
    PersonsModel toPersonsModel(Person person);
}