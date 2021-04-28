package com.spehof.piggy.domain.moneymovement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.domain.Account;
import com.spehof.piggy.domain.BaseEntity;
import com.spehof.piggy.domain.category.EarningCategory;
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
@EqualsAndHashCode(of = {"id"})
public class Earning extends BaseEntity {

    public Earning(Account account,
                   EarningCategory earningCategory,
                   MoneyHolder moneyHolder,
                   Long amount){
        this.account = account;
        this.moneyHolder = moneyHolder;
        this.earningCategory = earningCategory;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "earning_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "account_ID")
    @JsonIgnore
    private Account account;

    @OneToOne
    @JoinColumn(name = "money_holder_ID", referencedColumnName = "money_holder_ID")
    private MoneyHolder moneyHolder;

    @OneToOne
    @JoinColumn(name = "earning_category_ID", referencedColumnName = "earning_category_ID")
    private EarningCategory earningCategory;

    @Column(name = "amount")
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
