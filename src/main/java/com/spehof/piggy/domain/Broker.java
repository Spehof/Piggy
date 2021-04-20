package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.BrokerSubAccountNotFoundException;
import com.spehof.piggy.exception.BudgetNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Entity
@Table(name = "brokers")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Broker extends BaseEntity {

    public Broker(Account account, String brokerName){
        this.account = account;
        this.brokerName = brokerName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_broker")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_account")
    @JsonIgnore
    Account account;

//    BrokerType brokerType;
    private String brokerName;

    @OneToMany()
    private List<BrokerSubAccount> brokerSubAccounts = new ArrayList<>();


    public void setAccount(Account account) {
        //prevent endless loop
        if (sameAsFormer(this.account, account))
            return ;
        //set new owner
        Account oldAccount = this.account;
        this.account = account;
        //remove from the old owner
        if (oldAccount!=null)
            oldAccount.removeBroker(this);
        //set myself into new owner
        if (account!=null)
            account.setBrokers(Collections.singletonList(this));
    }

    public void setBrokerSubAccount(BrokerSubAccount brokerSubAccount) {
        //prevent endless loop
        if (this.brokerSubAccounts.contains(brokerSubAccount))
            return ;
        //add new brokerSubAccount
        this.brokerSubAccounts.add(brokerSubAccount);
        //set myself into the brokerSubAccount account
        brokerSubAccount.setBroker(this);
    }

    public void removeBrokerSubAccount(BrokerSubAccount brokerSubAccount) {
        //prevent endless loop
        if (!brokerSubAccounts.contains(brokerSubAccount))
            return ;
        brokerSubAccounts.remove(brokerSubAccount);
        brokerSubAccount.setBroker(null);
    }

    public BrokerSubAccount getBrokerSubAccount(Long brokerSubAccountId) {
        return this.brokerSubAccounts.stream()
                .filter(brokerSubAccount -> brokerSubAccount.id.equals(brokerSubAccountId))
                .findFirst()
                .orElseThrow(BrokerSubAccountNotFoundException::new);
    }
}
