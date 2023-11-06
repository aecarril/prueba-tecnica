package com.pichincha.accounts.domain.interfaces.repositories;

import com.pichincha.accounts.domain.entities.Client;

import java.util.Optional;

public interface IClientRepository {

    Optional<Client> getById(String id);
}
