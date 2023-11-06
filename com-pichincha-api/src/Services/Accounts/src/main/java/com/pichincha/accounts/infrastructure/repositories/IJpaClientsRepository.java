package com.pichincha.accounts.infrastructure.repositories;

import com.pichincha.accounts.infrastructure.models.ClientsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaClientsRepository extends JpaRepository<ClientsModel, String> {
}
