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
@Table(name = "costs")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Cost extends BaseEntity{

    public Cost(Account account,CostCategory costCategory, MoneyHolder moneyHolder, Long amount){
        this.account = account;
        this.costCategory = costCategory;
        this.moneyHolder = moneyHolder;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "account_ID")
    @JsonIgnore
    private Account account;

    @OneToOne
    @JoinColumn(name = "money_holder_ID", referencedColumnName = "money_holder_ID")
    private MoneyHolder moneyHolder;

    @OneToOne
    @JoinColumn(name = "cost_category_ID", referencedColumnName = "cost_category_ID")
    private CostCategory costCategory;

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
            oldAccount.removeCost(this);
        //set myself into new owner
        if (account!=null)
            account.setCosts(Collections.singletonList(this));
    }

    public void setCostCategory(CostCategory costCategory) {
        this.costCategory = costCategory;
    }

    public void setMoneyHolder(MoneyHolder moneyHolder) {
        this.moneyHolder = moneyHolder;
    }
}
