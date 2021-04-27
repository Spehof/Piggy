package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Goal;
import com.spehof.piggy.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 18/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/goals")
public class GoalController {

    private final GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<Goal> getAll(@PathVariable(name = "id") Account account){
        return goalService.getAll(account.getUser());
    }

    @PostMapping
    public Goal createGoal(@PathVariable(name = "id") Account account,
                           @RequestBody Goal goalFromApi){
        return goalService.create(account.getUser(), goalFromApi.getAmount(), goalFromApi.getTitle());
    }

    @DeleteMapping
    public void deleteGoal(@PathVariable(name = "id") Account account,
                           @RequestBody Goal goalFromApi){
        goalService.delete(account.getUser(), goalFromApi);
    }

    @PutMapping()
    public Goal update(@PathVariable(name = "id") Account account,
                       @RequestBody Goal goalFromApi){
        return goalService.update(account.getUser(), goalFromApi);
    }
}
