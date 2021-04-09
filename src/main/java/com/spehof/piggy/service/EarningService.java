package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Earning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Earning create(Account account, Long amount){
        Earning earning = new Earning(account, amount);
        return earningDao.save(earning);
    }
}
