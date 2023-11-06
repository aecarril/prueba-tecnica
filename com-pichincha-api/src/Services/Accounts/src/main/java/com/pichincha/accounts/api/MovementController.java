package com.pichincha.accounts.api;

import com.pichincha.accounts.application.commands.CreateMovementCommand;
import com.pichincha.accounts.application.commands.DeleteMovementCommand;
import com.pichincha.accounts.application.commands.DepositCommand;
import com.pichincha.accounts.application.commands.UpdateMovementCommand;
import com.pichincha.accounts.application.commands.WithdrawCommand;
import com.pichincha.accounts.application.dtos.AccountStatusResponse;
import com.pichincha.accounts.application.queries.ReadAccountStatusQuery;
import com.pichincha.accounts.application.queries.ReadMovementQuery;
import com.pichincha.accounts.domain.entities.Movement;
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
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovementController {

    private static final Logger logger = LoggerFactory.getLogger(MovementController.class);
    private static final String MESSAGE_LOG = "----- Sending query: {} {}";
    private final Mediator mediator;

    public MovementController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{movement}")
    public ResponseEntity<Movement> getById(
            @PathVariable("movement") String movementId) {

        var query = new ReadMovementQuery(movementId);
        logger.info(MESSAGE_LOG, query.getClass().getName(), query);
        return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
    }

    @GetMapping("/estado-cuenta/{clientid}")
    public ResponseEntity<List<AccountStatusResponse>> getAccountStatus(
            @PathVariable("clientid") String clientid) {

        var query = new ReadAccountStatusQuery(clientid);
        logger.info(MESSAGE_LOG, query.getClass().getName(), query);
        return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveMovement(
            @Valid @RequestBody CreateMovementCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.CREATED);
    }
    
    @PutMapping()
    public ResponseEntity<Movement> updateMovement(
            @Valid @RequestBody UpdateMovementCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.OK);
    }
    
    @DeleteMapping("/{movement}")
    public ResponseEntity<Void> disableMovement(@PathVariable("movement") String movementId) {
        
        var command = new DeleteMovementCommand(movementId);
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        this.mediator.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/retiro")
    public ResponseEntity<Void> withdrawMoney(
            @Valid @RequestBody WithdrawCommand command) {

        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        this.mediator.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> depositMoney(
            @Valid @RequestBody DepositCommand command) {

        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        this.mediator.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
