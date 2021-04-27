package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Loan;
import com.spehof.piggy.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{idAccount}/friends/{idFriend}/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAll(@PathVariable(name = "idAccount") Account account,
                             @PathVariable(name = "idFriend") Friend friend){
        return loanService.getAll(account.getUser(), friend);
    }

    @PostMapping
    public Loan createLoan(@PathVariable(name = "idAccount") Account account,
                           @PathVariable(name = "idFriend") Friend friend,
                           @RequestBody Loan loanFromApi){
        return loanService.create(account.getUser().getFriend(friend.getId()), loanFromApi.getAmount());
    }

    @DeleteMapping
    public void deleteLoan(@PathVariable(name = "idAccount") Account account,
                           @PathVariable(name = "idFriend") Friend friend,
                           @RequestBody Loan loanFromApi) {
        loanService.delete(account.getUser(), friend, loanFromApi);
    }

    @PutMapping
    public Loan updateLoan(@PathVariable(name = "idAccount") Account account,
                           @PathVariable(name = "idFriend") Friend friend,
                           @RequestBody Loan loanFromApi){
        return loanService.update(account.getUser(), friend, loanFromApi);
    }
}
