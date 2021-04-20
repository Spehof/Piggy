package com.spehof.piggy.service;

import com.spehof.piggy.DAO.GoalDao;
import com.spehof.piggy.domain.Client;
import com.spehof.piggy.domain.Goal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        client.setGoal(goal);
        return goalDao.save(goal);
    }

    public List<Goal> getAll(Client client) {
        return client.getGoals();
    }

    public void delete(Client client, Goal goalFromApi) {
        client.removeGoal(goalFromApi);
        goalDao.delete(goalFromApi);
    }

    public Goal update(Client client, Goal goalFromApi) {
        Goal goalFromDb = client.getGoal(goalFromApi.getId());
        BeanUtils.copyProperties(goalFromApi, goalFromDb, "id", "client");
        return goalDao.save(goalFromDb);
    }
}
