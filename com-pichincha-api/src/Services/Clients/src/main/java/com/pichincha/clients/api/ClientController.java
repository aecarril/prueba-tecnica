package com.pichincha.clients.api;

import com.pichincha.clients.application.commands.CreateClientCommand;
import com.pichincha.clients.application.commands.DeleteClientCommand;
import com.pichincha.clients.application.commands.UpdateClientCommand;
import com.pichincha.clients.application.dtos.CreateClientResponse;
import com.pichincha.clients.application.queries.ReadClientQuery;
import com.pichincha.clients.domain.entities.Client;
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
@RequestMapping("/clientes")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private static final String MESSAGE_LOG = "----- Sending query: {} {}";
    private final Mediator mediator;

    public ClientController(Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(
            @PathVariable("id") String id) {

        var query = new ReadClientQuery(id);
        logger.info(MESSAGE_LOG, query.getClass().getName(), query);
        return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<CreateClientResponse> saveClient(
            @Valid @RequestBody CreateClientCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.CREATED);
    }
    
    @PutMapping()
    public ResponseEntity<Client> updateClient(
            @Valid @RequestBody UpdateClientCommand command) {
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        return new ResponseEntity<>(this.mediator.dispatch(command), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableClient(@PathVariable("id") String accountNumber) {
        
        var command = new DeleteClientCommand(accountNumber);
        
        logger.info(MESSAGE_LOG, command.getClass().getName(), command);
        this.mediator.dispatch(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
