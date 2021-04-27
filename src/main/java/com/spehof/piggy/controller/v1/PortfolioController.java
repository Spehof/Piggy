package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Portfolio;
import com.spehof.piggy.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<Portfolio> getAll(@PathVariable(name = "id") Account account){
        return portfolioService.getAll(account.getUser());
    }

    @GetMapping("{portfolioId}")
    public Portfolio getOne(@PathVariable(name = "id") Account account,
                            @PathVariable(name = "portfolioId") Portfolio portfolio){
        return portfolioService.getOne(account.getUser(), portfolio);
    }

    @PostMapping
    public Portfolio create(@PathVariable(name = "id") Account account,
                            @RequestBody Portfolio portfolioFromApi){
        return portfolioService.create(account.getUser(), portfolioFromApi.getTitle());
    }

    @DeleteMapping
    public void delete(@PathVariable(name = "id") Account account,
                       @RequestBody Portfolio portfolioFromApi){
        portfolioService.delete(account.getUser(), portfolioFromApi);
    }

    @PutMapping
    public Portfolio update(@PathVariable(name = "id") Account account,
                            @RequestBody Portfolio portfolioFromApi){
        return portfolioService.update(account.getUser(), portfolioFromApi);
    }
}
