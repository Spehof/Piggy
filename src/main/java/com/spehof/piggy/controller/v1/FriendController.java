package com.spehof.piggy.controller.v1;

import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.Friend;
import com.spehof.piggy.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Spehof
 * @created 15/04/2021
 */
@RestController
@RequestMapping("/api/v1/account/{id}/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping
    public List<Friend> getAll(@PathVariable(name = "id")Account account){
        return friendService.getAll(account.getUser());
    }

    @GetMapping("{friendId}")
    public Friend getOne(@PathVariable(name = "id")Account account,
                         @PathVariable(name = "friendId") Long friendId){
        return friendService.getOne(account.getUser(), friendId);
    }

    @PostMapping
    public Friend createNewFriend(@PathVariable(name = "id")Account account,
                                  @RequestBody Friend friendFromApi){
        return friendService.create(account.getUser(), friendFromApi.getName());
    }

    @PutMapping()
    public Friend updateFriend(@PathVariable(name = "id")Account account,
                               @RequestBody Friend friendFromApi){
        return friendService.update(account.getUser(), friendFromApi);
    }
//    TODO rewrite equals and hash code
    @DeleteMapping
    public void deleteFriend(@PathVariable(name = "id")Account account,
                             @RequestBody Friend friendFromApi){
        friendService.delete(account.getUser(), friendFromApi);
    }
}
