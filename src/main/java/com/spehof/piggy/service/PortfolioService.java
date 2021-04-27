package com.spehof.piggy.service;

import com.spehof.piggy.DAO.PortfolioDao;
import com.spehof.piggy.domain.Asset;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.Portfolio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Portfolio create(User user, String title){
        Portfolio portfolio = new Portfolio(user, title);
        user.setPortfolio(portfolio);
        return portfolioDao.save(portfolio);
    }

    public List<Portfolio> getAll(User user){
        return user.getPortfolios();
    }

    public Portfolio getOne(User user, Portfolio portfolioFromApi){
        return user.getPortfolio(portfolioFromApi.getId());
    }

    public Portfolio addToPortfolio(Portfolio portfolio, Asset asset){
        portfolio.setAsset(asset);
        return portfolioDao.save(portfolio);
    }

    public void delete(User user, Portfolio portfolioFromApi){
        user.removePortfolio(portfolioFromApi);
        portfolioDao.delete(portfolioFromApi);
    }

    public Portfolio update(User user, Portfolio portfolioFromApi){
        Portfolio portfolioFromDb = user.getPortfolio(portfolioFromApi.getId());
        BeanUtils.copyProperties(portfolioFromApi, portfolioFromDb, "id", "client");
        return portfolioDao.save(portfolioFromDb);
    }
}
