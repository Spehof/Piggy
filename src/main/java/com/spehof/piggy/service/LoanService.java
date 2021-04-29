package com.spehof.piggy.service;

import com.spehof.piggy.dao.LoanDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Loan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        friend.setLoan(loan);
        return loanDao.save(loan);
    }

    public List<Loan> getAll(User user, Friend friend) {
        return friend.getLoans();
    }

    public void delete(User user, Friend friend, Loan loanFromApi) {
        user.getFriend(friend.getId()).removeLoan(loanFromApi);
        loanDao.delete(loanFromApi);
    }

    public Loan update(User user, Friend friend, Loan loanFromApi) {
        Loan loanFromDb = user.getFriend(friend.getId()).getLoan(loanFromApi.getId());
        BeanUtils.copyProperties(loanFromApi, loanFromDb, "id", "friend");
        return loanDao.save(loanFromDb);
    }
}
