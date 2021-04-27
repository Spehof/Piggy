package com.spehof.piggy.service;

import com.spehof.piggy.DAO.OweDao;
import com.spehof.piggy.domain.User;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.domain.Owe;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        friend.setOwe(owe);
        return oweDao.save(owe);
    }

    public List<Owe> getAll(User user, Friend friend) {
        return user.getFriend(friend.getId()).getOwes();
    }

    public void delete(User user, Friend friend, Owe oweFromApi) {
        user.getFriend(friend.getId()).removeOwe(oweFromApi);
        oweDao.delete(oweFromApi);
    }

    public Owe update(User user, Friend friend, Owe oweFromApi) {
        Owe oweFromDb = user.getFriend(friend.getId()).getOwe(oweFromApi.getId());
        BeanUtils.copyProperties(oweFromApi, oweFromDb, "id", "friend");
        return oweDao.save(oweFromDb);
    }
}
