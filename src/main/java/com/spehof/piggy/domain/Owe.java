package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Data
@Table(name = "owes")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Owe extends BaseEntity {

    public Owe(Friend friend, Long amount){
        this.friend = friend;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_owe")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_friend")
    @JsonIgnore
    Friend friend;

    Long amount;

    public void setFriend(Friend friend) {
//        //prevent endless loop
//        if (this.friend != null && sameAsFormer(this.friend, friend))
//            return;
//        // set new client account
//        Friend oldFriend = this.friend;
//        this.friend = friend;
//        //remove from the old client account
//        if (oldFriend!=null)
////            TODO refactor all this fucking crap with null
//            oldFriend.setLoans(null);
//        //set myself into new client account
//        if (friend!=null)
//            friend.setOwes(Collections.singletonList(this));
        this.friend = friend;
    }
}
