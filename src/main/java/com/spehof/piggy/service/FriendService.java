package com.spehof.piggy.service;

import com.spehof.piggy.DAO.FriendDao;
import com.spehof.piggy.domain.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class FriendService {

    private final FriendDao friendDao;
    private final LoanService loanService;
    private final OweService oweService;

    @Autowired
    public FriendService(FriendDao friendDao,
                         LoanService loanService,
                         OweService oweService) {

        this.friendDao = friendDao;
        this.loanService = loanService;
        this.oweService = oweService;
    }

    public Friend create(Client client, String name){
        Friend friend = new Friend(client, name);
        friendDao.save(friend);
//        TODO for test!!
        friend.setLoan(loanService.create(friend, 10000L));
//        TODO for test!!
        friend.setOwe(oweService.create(friend, 5000L));


//        TODO for testing saving friend
        client.setFriend(friend);
        return friendDao.save(friend);
    }

    public List<Friend> getAll(Client client) {
        return client.getFriends();
    }

    public Friend getOne(Client client, Long friendId) {
        return client.getFriend(friendId);
    }

    public Friend update(Client client, Friend newFriend) {
        Friend friendFromDb = client.getFriend(newFriend.getId());
        BeanUtils.copyProperties(newFriend, friendFromDb, "id", "client");
        return friendDao.save(friendFromDb);
    }

    public void delete(Client client, Friend friend) {
        Friend friendFromDb = client.getFriend((friend.getId()));
        client.removeFriend(friendFromDb);
        friendDao.delete(friendFromDb);
    }
}
