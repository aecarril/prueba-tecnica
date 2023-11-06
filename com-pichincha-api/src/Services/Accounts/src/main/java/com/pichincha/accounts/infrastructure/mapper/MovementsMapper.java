package com.pichincha.accounts.infrastructure.mapper;

import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.infrastructure.models.MovementsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementsMapper {

    @Mapping(source = "id", target = "movementId")
    @Mapping(source = "movementsDate", target = "movementDate")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "movementsValue", target = "movementValue")
    @Mapping(source = "balance", target = "balance")
    @Mapping(source = "accountNumber", target = "accountNumber")
    Movement toMovement(MovementsModel movementModel);

    List<Movement> toMovements(List<MovementsModel> movementModels);

    @InheritInverseConfiguration
    MovementsModel toMovementsModel(Movement movement);
}