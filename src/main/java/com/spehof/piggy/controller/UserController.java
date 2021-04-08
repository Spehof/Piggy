package com.spehof.piggy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spehof.piggy.DAO.UserDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.exception.UserNotFoundException;
import com.spehof.piggy.utils.AccountViews;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@RestController
@RequestMapping("user")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }
//    List<User> users = new ArrayList()
//    {{
//        add( new User("Mike"));
//        add( new User(2L, "Jake"));
//        add( new User(3L, "Simon"));
//        add( new User(4L, "Fern"));
//        add( new User(5L, "Nikolas"));
//        add( new User(6L, "Billy"));
//    }};


//    private User getUserById(Long id) {
//        return users.stream()
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElseThrow(UserNotFoundException::new);
//    }

    @GetMapping("all")
    public List<Client> getAll(){
        return userDao.findAll();
    }

    /**
     * Automatically  find Message instance by id in URL
     * @PathVariable("id") - ask spring what finding by id
     */
    @GetMapping("{id}")
    public Client getOne(@PathVariable("id") Client client){
        if (client.getId() != null) {
            return client;
        } else {
            throw new UserNotFoundException();
        }
    }

    @PostMapping
    public Client create(@RequestBody Client client){
        client.setRegistrationDate(LocalDateTime.now());
        Account account = new Account();
        account.setCurrency(2);
        client.setAccount(account);
        return userDao.save(client);
    }

    @PutMapping("{id}")
    public Client update(
            @PathVariable("id") Client clientFromDb,
            @RequestBody Client client){

        BeanUtils.copyProperties(client, clientFromDb, "id");
        return userDao.save(clientFromDb);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Client client){
        userDao.delete(client);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}
