package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Entity
@Table(name = "money_holders")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class MoneyHolder extends BaseEntity {

    public MoneyHolder(Account account, MoneyHolderType moneyHolderType, String name){
        this.account = account;
        this.moneyHolderType = moneyHolderType;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_money_holder")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_account")
    private Account account;

    @ManyToOne()
    @JoinColumn(name = "id_money_holder_type")
    private MoneyHolderType moneyHolderType;

    private String name;

    public void setMoneyHolderType(MoneyHolderType moneyHolderType) {
        //prevent endless loop
        if (sameAsFormer(this.moneyHolderType, moneyHolderType))
            return;
        MoneyHolderType oldMoneyHolderType = this.moneyHolderType;
        this.moneyHolderType = moneyHolderType;
        if (oldMoneyHolderType!=null)
            oldMoneyHolderType.setMoneyHolder(null);
        if (moneyHolderType!=null)
            moneyHolderType.setMoneyHolder(this);
    }
}
