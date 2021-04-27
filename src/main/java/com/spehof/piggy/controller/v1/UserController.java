package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.User;
import com.spehof.piggy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("all")
    public List<User> getAll(){
        return userService.getAll();
    }

    /**
     * Automatically  find Message instance by id in URL
     * @PathVariable("id") - ask spring what finding by id
     */
    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user){
            return user;
    }

    @PostMapping
    public User create(@RequestBody User userFromApi){

        return userService.create(userFromApi);
    }

    @PutMapping("{id}")
    public User update(
            @PathVariable("id") User userFromDb,
            @RequestBody User userFromApi){
        return userService.update(userFromDb, userFromApi);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") User user){
        userService.delete(user);
    }


}
