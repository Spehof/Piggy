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
@Table(name = "budgets")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Budget extends BaseEntity {

    public Budget(User user, Long value){
        this.user = user;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_ID")
    @JsonIgnore
    private User user;

    @Column(name = "value")
    private Long value;

    public void setUser(User user) {
        //prevent endless loop
        if (sameAsFormer(this.user, user))
            return ;
        //set new owner
        User oldUser = this.user;
        this.user = user;
        //remove from the old owner
        if (oldUser !=null)
            oldUser.removeBudget(this);
        //set myself into new owner
        if (user !=null)
            user.setBudgets(Collections.singletonList(this));
    }

}
