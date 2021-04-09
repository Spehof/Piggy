package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Data
@Table(name = "loans")
@EqualsAndHashCode(of = {"id"})
public class Loan extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    Long id;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "friend_id")
    Friend friend;

    Long amount;

    public void setFriend(Friend friend) {
        //prevent endless loop
        if (this.friend != null && sameAsFormer(this.friend, friend))
            return;
        // set new client account
        Friend oldFriend = this.friend;
        this.friend = friend;
        //remove from the old client account
        if (oldFriend!=null)
//            TODO refactor all this fucking crap with null
            oldFriend.setLoans(null);
        //set myself into new client account
        if (friend!=null)
            friend.setLoans(Collections.singletonList(this));
    }
}
