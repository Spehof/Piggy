package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Loan;
import com.spehof.piggy.domain.Owe;
import com.spehof.piggy.service.OweService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{idAccount}/friends/{idFriend}/owes")
public class OweController {

    private final OweService oweService;

    @Autowired
    public OweController(OweService oweService) {
        this.oweService = oweService;
    }

    @GetMapping
    public List<Owe> getAll(@PathVariable(name = "idAccount") Account account,
                            @PathVariable(name = "idFriend") Friend friend){
        return oweService.getAll(account.getClient(), friend);
    }

    @PostMapping
    public Owe creteOwe(@PathVariable(name = "idAccount") Account account,
                        @PathVariable(name = "idFriend") Friend friend,
                        @RequestBody Owe owe){
        return oweService.create(account.getClient().getFriend(friend.getId()), owe.getAmount());
    }

    @DeleteMapping
    public void deleteOwe(@PathVariable(name = "idAccount") Account account,
                          @PathVariable(name = "idFriend") Friend friend,
                          @RequestBody Owe owe){
        oweService.delete(account.getClient(), friend, owe);
    }

    @PutMapping
    public Owe updateOwe(@PathVariable(name = "idAccount") Account account,
                         @PathVariable(name = "idFriend") Friend friend,
                         @RequestBody Owe owe){
        return oweService.update(account.getClient(), friend, owe);
    }
}
