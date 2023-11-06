package com.pichincha.clients.infrastructure.repositories;

import com.pichincha.clients.infrastructure.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaClientsRepository extends JpaRepository<ClientsModel, String> {
    Long countByPersonsModelIdentificationAndStatus(String identification, Boolean status);
}
