package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Earning;
import com.spehof.piggy.service.EarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
