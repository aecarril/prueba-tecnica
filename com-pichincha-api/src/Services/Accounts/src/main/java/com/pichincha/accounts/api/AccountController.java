package com.pichincha.accounts.api;

import com.pichincha.accounts.application.commands.CreateAccountCommand;
import com.pichincha.accounts.application.commands.DeleteAccountCommand;
import com.pichincha.accounts.application.commands.UpdateAccountCommand;
import com.pichincha.accounts.application.queries.ReadAccountQuery;
import com.pichincha.accounts.domain.entities.Account;
import io.jkratz.mediator.core.Mediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private static final String MESSAGE_LOG = "----- Sending query: {} {}";
    private final Mediator mediator;

    public AccountController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{account}")
    public ResponseEntity<Account> getById(
            @PathVariable("account") String accountNumber) {

        var query = new ReadAccountQuery(accountNumber);
        logger.info(MESSAGE_LOG, query.getClass().getName(), query);
        return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<String> saveAccount(
            @Valid @RequestBody CreateAccountCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.CREATED);
    }
    
    @PutMapping()
    public ResponseEntity<Account> updateAccount(
            @Valid @RequestBody UpdateAccountCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.OK);
    }
    
    @DeleteMapping("/{account}")
    public ResponseEntity<Void> disableAccount(@PathVariable("account") String accountNumber) {
        
        var command = new DeleteAccountCommand(accountNumber);
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        this.mediator.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
