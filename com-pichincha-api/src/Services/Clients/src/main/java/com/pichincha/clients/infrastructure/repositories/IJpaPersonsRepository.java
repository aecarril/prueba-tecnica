package com.pichincha.clients.infrastructure.repositories;

import com.pichincha.clients.infrastructure.models.PersonsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaPersonsRepository extends JpaRepository<PersonsModel, String> {

}
