package com.spehof.piggy.service;

import com.spehof.piggy.DAO.PortfolioDao;
import com.spehof.piggy.domain.Asset;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Portfolio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Service
public class PortfolioService {

    private final PortfolioDao portfolioDao;

    @Autowired
    public PortfolioService(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao;
    }

    public Portfolio create(Client client, String title){
        Portfolio portfolio = new Portfolio(client, title);
        client.setPortfolio(portfolio);
        return portfolioDao.save(portfolio);
    }

    public List<Portfolio> getAll(Client client){
        return client.getPortfolios();
    }

    public Portfolio getOne(Client client, Portfolio portfolioFromApi){
        return client.getPortfolio(portfolioFromApi.getId());
    }

    public Portfolio addToPortfolio(Portfolio portfolio, Asset asset){
        portfolio.setAsset(asset);
        return portfolioDao.save(portfolio);
    }

    public void delete(Client client, Portfolio portfolioFromApi){
        client.removePortfolio(portfolioFromApi);
        portfolioDao.delete(portfolioFromApi);
    }

    public Portfolio update(Client client, Portfolio portfolioFromApi){
        Portfolio portfolioFromDb = client.getPortfolio(portfolioFromApi.getId());
        BeanUtils.copyProperties(portfolioFromApi, portfolioFromDb, "id", "client");
        return portfolioDao.save(portfolioFromDb);
    }
}
