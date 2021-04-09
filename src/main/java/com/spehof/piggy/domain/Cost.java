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
@Table(name = "costs")
@Data
@EqualsAndHashCode(of = {"id", "amount"})
public class Cost extends BaseEntity{

    @Id
    @Column(name = "account_id")
    Long id;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "account_id")
    Account account;

    Long amount;

    public void setAccount(Account account) {
        //prevent endless loop
        if (sameAsFormer(this.account, account))
            return ;
        //set new owner
        Account oldAccount = this.account;
        this.account = account;
        //remove from the old owner
        if (oldAccount!=null)
            oldAccount.removeCost(this);
        //set myself into new owner
        if (account!=null)
            account.setCosts(Collections.singletonList(this));
    }
}
