package com.spehof.piggy.controller.v1.moneymovement;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.moneymovement.Earning;
import com.spehof.piggy.service.moneymovement.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/earnings")
public class EarningController {

    private final EarningService earningService;

    @Autowired
    public EarningController(EarningService earningService) {
        this.earningService = earningService;
    }

    @GetMapping
    public List<Earning> getAll(@PathVariable(name = "id") Account account){
        return earningService.getAll(account);
    }

    @PostMapping
    public Earning createEarning(@PathVariable(name = "id") Account account,
                                 @RequestBody Earning earningFromApi){
        return earningService.create(account,
                earningFromApi.getEarningCategory(),
                earningFromApi.getMoneyHolder(),
                earningFromApi.getAmount());
    }

//    TODO deleting across ID or something else
    @DeleteMapping
    public void deleteEarning(@PathVariable(name = "id") Account account,
                              @RequestBody Earning earningFromApi){
        earningService.delete(account, earningFromApi);
    }

    @PutMapping()
    public Earning updateEarning(@PathVariable(name = "id") Account account,
                                 @RequestBody Earning earningFromApi){
        return earningService.update(account, earningFromApi);
    }
}
