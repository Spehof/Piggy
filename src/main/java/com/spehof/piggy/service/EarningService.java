package com.spehof.piggy.service;

import com.spehof.piggy.DAO.EarningDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Earning;
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

    public Earning create(Account account, Long amount){
        Earning earning = new Earning(account, amount);
        account.setEarning(earning);
        return earningDao.save(earning);
    }

    public List<Earning> getAll(Account account) {
        return account.getEarnings();
    }

    public void delete(Account account, Earning earning) {
        account.removeEarning(earning);
        earningDao.delete(earning);
    }

    public Earning update(Account account, Earning newEarning) {
        Earning earningFromDb = account.getEarning(newEarning.getId());
        BeanUtils.copyProperties(newEarning, earningFromDb, "id", "account");
        return earningDao.save(earningFromDb);
    }
}
