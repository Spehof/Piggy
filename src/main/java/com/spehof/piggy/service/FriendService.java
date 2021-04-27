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

    public Friend create(User user, String name){
        Friend friend = new Friend(user, name);
        friendDao.save(friend);
//        TODO for test!!
        friend.setLoan(loanService.create(friend, 10000L));
//        TODO for test!!
        friend.setOwe(oweService.create(friend, 5000L));


//        TODO for testing saving friend
        user.setFriend(friend);
        return friendDao.save(friend);
    }

    public List<Friend> getAll(User user) {
        return user.getFriends();
    }

    public Friend getOne(User user, Long friendId) {
        return user.getFriend(friendId);
    }

    public Friend update(User user, Friend friendFromApi) {
        Friend friendFromDb = user.getFriend(friendFromApi.getId());
        BeanUtils.copyProperties(friendFromApi, friendFromDb, "id", "user", "loans", "owes");
        return friendDao.save(friendFromDb);
    }

    public void delete(User user, Friend friendFromApi) {
        Friend friendFromDb = user.getFriend((friendFromApi.getId()));
        user.removeFriend(friendFromDb);
        friendDao.delete(friendFromDb);
    }
}
