package com.spehof.piggy.controller.v1.trading.broker;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.trading.Asset;
import com.spehof.piggy.domain.trading.Portfolio;
import com.spehof.piggy.service.trading.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/portfolios/{portfolioId}/assets")
public class PortfolioAssetController {

    private final AssetService assetService;

    @Autowired
    public PortfolioAssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> getAllFromPortfolio(@PathVariable(name = "id")Account account,
                                           @PathVariable(name = "portfolioId")Portfolio portfolio){
        return assetService.getAllFromPortfolio(account.getUser().getPortfolio(portfolio.getId()));
    }

    @PostMapping
    public Asset addToPortfolio(@PathVariable(name = "id")Account account,
                                @PathVariable(name = "portfolioId")Portfolio portfolio,
                                @RequestBody Asset assetFromApi){
        return assetService.addToPortfolio(account.getUser().getPortfolio(portfolio.getId()), assetFromApi);
    }
    @DeleteMapping
    public void deleteFromPortfolio(@PathVariable(name = "id")Account account,
                                    @PathVariable(name = "portfolioId")Portfolio portfolio,
                                    @RequestBody Asset assetFromApi){
        assetService.deleteFromPortfolio(account.getUser().getPortfolio(portfolio.getId()), assetFromApi);
    }

}
