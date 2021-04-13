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
@Table(name = "earnings")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id", "amount"})
public class Earning extends BaseEntity{

    public Earning(Account account,
                   Long amount){
        this.account = account;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_earning")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_account")
    @JsonIgnore
    private Account account;

    private Long amount;

    public void setAccount(Account account) {
        //prevent endless loop
        if (sameAsFormer(this.account, account))
            return ;
        //set new owner
        Account oldAccount = this.account;
        this.account = account;
        //remove from the old owner
        if (oldAccount!=null)
            oldAccount.removeEarning(this);
        //set myself into new owner
        if (account!=null)
            account.setEarnings(Collections.singletonList(this));
    }
}
