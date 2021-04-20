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
        return friendService.getAll(account.getClient());
    }

    @GetMapping("{friendId}")
    public Friend getOne(@PathVariable(name = "id")Account account,
                         @PathVariable(name = "friendId") Long friendId){
        return friendService.getOne(account.getClient(), friendId);
    }

    @PostMapping
    public Friend createNewFriend(@PathVariable(name = "id")Account account,
                                  @RequestBody Friend friend){
        return friendService.create(account.getClient(), friend.getName());
    }

    @PutMapping()
    public Friend updateFriend(@PathVariable(name = "id")Account account,
                               @RequestBody Friend newFriend){
        return friendService.update(account.getClient(), newFriend);
    }
//    TODO rewrite equals and hash code
    @DeleteMapping
    public void deleteFriend(@PathVariable(name = "id")Account account,
                             @RequestBody Friend friend){
        friendService.delete(account.getClient(), friend);
    }
}
