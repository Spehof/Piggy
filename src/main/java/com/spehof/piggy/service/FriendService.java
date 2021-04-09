package com.spehof.piggy.service;

import com.spehof.piggy.DAO.FriendDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.MoneyHolderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class FriendService {

    private final FriendDao friendDao;

    @Autowired
    public FriendService(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    public Friend create(Client client, String name){
        Friend friend = new Friend(client, name);
        return friendDao.save(friend);
    }
}
