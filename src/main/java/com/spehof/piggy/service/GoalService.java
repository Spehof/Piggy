package com.spehof.piggy.service;

import com.spehof.piggy.DAO.GoalDao;
import com.spehof.piggy.domain.User;
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

    public Goal create(User user, Long amount, String text){
        Goal goal = new Goal(user, amount, text);
        user.setGoal(goal);
        return goalDao.save(goal);
    }

    public List<Goal> getAll(User user) {
        return user.getGoals();
    }

    public void delete(User user, Goal goalFromApi) {
        user.removeGoal(goalFromApi);
        goalDao.delete(goalFromApi);
    }

    public Goal update(User user, Goal goalFromApi) {
        Goal goalFromDb = user.getGoal(goalFromApi.getId());
        BeanUtils.copyProperties(goalFromApi, goalFromDb, "id", "client");
        return goalDao.save(goalFromDb);
    }
}
