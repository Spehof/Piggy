package com.spehof.piggy.service;

import com.spehof.piggy.DAO.GoalDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spehof
 * @created 10/04/2021
 */
@Service
public class GoalService {

    public final GoalDao goalDao;

    @Autowired
    public GoalService(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    public Goal create(Client client, Long amount, String text){
        Goal goal = new Goal(client, amount, text);
        return goalDao.save(goal);
    }
}
