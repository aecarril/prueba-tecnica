package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.infrastructure.models.PersonsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IJpaPersonsRepository extends JpaRepository<PersonsModel, String> {
    Optional<PersonsModel> findByIdentification(String identification);
}
