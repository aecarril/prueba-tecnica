package com.pichincha.accounts.application.commands;

import com.pichincha.accounts.domain.entities.Account;
import com.pichincha.accounts.domain.entities.Movement;
import com.pichincha.accounts.domain.exceptions.AccountsDomainException;
import com.pichincha.accounts.domain.interfaces.repositories.IAccountRepository;
import com.pichincha.accounts.domain.interfaces.repositories.IMovementRepository;
import io.jkratz.mediator.core.CommandHandler;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Component
public class WithdrawCommandHandler implements CommandHandler<WithdrawCommand> {
    private final IAccountRepository accountRepository;
    private final IMovementRepository movementRepository;

    public WithdrawCommandHandler(IAccountRepository accountRepository,
                                  IMovementRepository movementRepository) {
        this.accountRepository = accountRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    public void handle(@NotNull WithdrawCommand command) {

        var account = this.validateAccount(command.getAccountNumber());
        var newBalance =  withdrawBalance(account.getNumber(),
                account.getInitialBalance(),
                command.getMoney());

        if (newBalance >= 0) {
            var movement = new Movement();
            movement.setMovementDate(LocalDateTime.now());
            movement.setType("Retiro");
            movement.setMovementValue(command.getMoney() * -1d);
            movement.setBalance(newBalance);
            movement.setAccountNumber(account.getNumber());

            movementRepository.create(movement);
        } else {
            throw new AccountsDomainException("Saldo no disponible");
        }
    }

    private Account validateAccount(String accountNumber){
        var accountOptional = accountRepository.getByAccountNumber(accountNumber);

        if (!accountOptional.isPresent()) {
            throw new AccountsDomainException("La cuenta no existe");
        }

        return accountOptional.get();
    }

    private Double withdrawBalance(String accountNumber, Double initialBalance, Double money){

        var optionalLastMovement =
                movementRepository.getLastMovementByAccountNumber(accountNumber);
        var balance =  initialBalance;

        if (optionalLastMovement.isPresent()){
            var movement = optionalLastMovement.get();
            balance = movement.getBalance();
        }

        return balance - money;
    }
}
