package com.spehof.piggy.service;

import com.spehof.piggy.DAO.OweDao;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Owe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class OweService {

    private final OweDao oweDao;

    @Autowired
    public OweService(OweDao oweDao) {
        this.oweDao = oweDao;
    }

    public Owe create(Friend friend, Long amount){
        Owe owe = new Owe(friend, amount);
        return oweDao.save(owe);
    }
}
