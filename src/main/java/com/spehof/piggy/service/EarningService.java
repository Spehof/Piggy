package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Earning;
import com.spehof.piggy.domain.EarningCategory;
import com.spehof.piggy.domain.MoneyHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Service
public class EarningService {

    private final EarningDao earningDao;

    @Autowired
    public EarningService(EarningDao earningDao) {
        this.earningDao = earningDao;
    }

    public Earning create(Account account, EarningCategory earningCategory, MoneyHolder moneyHolder, Long amount){
        Earning earning = new Earning(account,earningCategory ,moneyHolder, amount);
        account.setEarning(earning);
        return earningDao.save(earning);
    }

    public List<Earning> getAll(Account account) {
        return account.getEarnings();
    }

    public void delete(Account account, Earning earningFromApi) {
        account.removeEarning(earningFromApi);
        earningDao.delete(earningFromApi);
    }

    public Earning update(Account account, Earning earningFromApi) {
        Earning earningFromDb = account.getEarning(earningFromApi.getId());
        BeanUtils.copyProperties(earningFromApi, earningFromDb, "id", "account");
        return earningDao.save(earningFromDb);
    }
}
