package com.pichincha.accounts.infrastructure.mapper;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.infrastructure.models.AccountsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { ClientsMapper.class })
public interface AccountsMapper {

    @Mapping(source = "number", target = "number")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "initialBalance", target = "initialBalance")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "clientsModel", target = "client")
    Account toAccount(AccountsModel accountModel);

    List<Account> toAccounts(List<AccountsModel> accountModels);

    @InheritInverseConfiguration
    AccountsModel toAccountsModel(Account account);
}