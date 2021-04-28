package com.spehof.piggy.service.moneymovement;

import com.spehof.piggy.DAO.moneymovement.TransactionDao;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.moneymovement.MoneyHolder;
import com.spehof.piggy.domain.moneymovement.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction create(Account account,
                              MoneyHolder fromMoneyHolderId,
                              MoneyHolder toMoneyHolderId,
                              BigDecimal amount){
        Transaction transaction = new Transaction(account, fromMoneyHolderId, toMoneyHolderId, amount);
        account.setTransaction(transaction);
        return transactionDao.save(transaction);
    }

    public List<Transaction> getAll(Account account){
        return account.getTransactions();
    }

    public void delete(Account account, Transaction transactionFromApi){
        Transaction transactionFromDb = account.getTransaction(transactionFromApi.getId());
        account.removeTransaction(transactionFromDb);
        transactionDao.delete(transactionFromDb);
    }

    public Transaction update(Account account, Transaction transactionFromApi){
        Transaction transactionFromDb = account.getTransaction(transactionFromApi.getId());
        BeanUtils.copyProperties(transactionFromApi, transactionFromDb,
                "id",
                "account",
                "fromMoneyHolderId",
                "toMoneyHolderId");
        return transactionDao.save(transactionFromDb);
    }
}
