package com.spehof.piggy.service;

import com.spehof.piggy.DAO.LoanDao;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class LoanService {

    private final LoanDao loanDao;

    @Autowired
    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    public Loan create(Friend friend, Long amount){
        Loan loan = new Loan(friend, amount);
        return loanDao.save(loan);
    }
}
