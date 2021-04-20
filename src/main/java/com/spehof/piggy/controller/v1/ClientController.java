package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Client;
import com.spehof.piggy.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {


    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping("all")
    public List<Client> getAll(){
        return clientService.getAll();
    }

    /**
     * Automatically  find Message instance by id in URL
     * @PathVariable("id") - ask spring what finding by id
     */
    @GetMapping("{id}")
    public Client getOne(@PathVariable("id") Client client){
            return client;
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @PutMapping("{id}")
    public Client update(
            @PathVariable("id") Client clientFromDb,
            @RequestBody Client clientFromApi){
        return clientService.update(clientFromDb, clientFromApi);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") Client client){
        clientService.delete(client);
    }


}
