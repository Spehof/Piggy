package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.BrokerSubAccountNotFoundException;
import com.spehof.piggy.exception.EarningNotFoundException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Entity
@Table(name = "portfolios")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Portfolio {

    public Portfolio(Client client, String title){
        this.client = client;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_portfolio")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    @JsonIgnore
    Client client;

    @ManyToMany
    List<BrokerSubAccount> brokerSubAccounts = new ArrayList<>();

    String title;

    public void setBrokerSubAccount(BrokerSubAccount brokerSubAccount) {
        //prevent endless loop
        if (this.brokerSubAccounts.contains(brokerSubAccount))
            return ;
        //add new earning
        this.brokerSubAccounts.add(brokerSubAccount);
        //set myself into the cost account
        brokerSubAccount.setPortfolio(this);
    }

    public void removeBrokerSubAccount(BrokerSubAccount brokerSubAccount) {
        //prevent endless loop
        if (!brokerSubAccounts.contains(brokerSubAccount))
            return ;
        //remove the account
        brokerSubAccounts.remove(brokerSubAccount);
        //remove myself from the twitter account
        brokerSubAccount.setPortfolio(null);
    }

    public BrokerSubAccount getBrokerSubAccount(Long brokerSubAccountId) {
        return this.brokerSubAccounts.stream()
                .filter(brokerSubAccount -> brokerSubAccount.getId().equals(brokerSubAccountId))
                .findFirst()
                .orElseThrow(BrokerSubAccountNotFoundException::new);
    }
}
