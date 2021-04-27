package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.BrokerAccountNotFoundException;
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

    public Broker(Account account, String brokerTitle){
        this.account = account;
        this.brokerTitle = brokerTitle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "account_ID", referencedColumnName = "account_ID")
    @JsonIgnore
    private Account account;

//    BrokerType brokerType;

    @Column(name = "title")
    private String brokerTitle;

    @OneToMany(mappedBy = "broker")
    private List<BrokerAccount> brokerAccounts = new ArrayList<>();


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

    public void setBrokerAccount(BrokerAccount brokerAccount) {
        //prevent endless loop
        if (this.brokerAccounts.contains(brokerAccount))
            return ;
        //add new brokerSubAccount
        this.brokerAccounts.add(brokerAccount);
        //set myself into the brokerSubAccount account
        brokerAccount.setBroker(this);
    }

    public void removeBrokerSubAccount(BrokerAccount brokerAccount) {
        //prevent endless loop
        if (!brokerAccounts.contains(brokerAccount))
            return ;
        brokerAccounts.remove(brokerAccount);
        brokerAccount.setBroker(null);
    }

    public BrokerAccount getBrokerAccount(Long brokerAccountId) {
        return this.brokerAccounts.stream()
                .filter(brokerAccount -> brokerAccount.getId().equals(brokerAccountId))
                .findFirst()
                .orElseThrow(BrokerAccountNotFoundException::new);
    }
}
