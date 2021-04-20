package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.EarningNotFoundException;
import com.spehof.piggy.exception.PortfolioNotFoundException;
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
@Table(name = "broker_sub_accounts")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class BrokerSubAccount extends BaseEntity {

    public BrokerSubAccount(Broker broker, String title){
        this.broker = broker;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_broker_sub_account")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_broker")
    @JsonIgnore
    Broker broker;

    @ManyToMany
    List<Portfolio> portfolios = new ArrayList<>();

    String title;

    public void setPortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (this.portfolios.contains(portfolio))
            return ;
        //add new earning
        this.portfolios.add(portfolio);
        //set myself into the cost account
        portfolio.setBrokerSubAccount(this);
    }

    public void removePortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (!portfolios.contains(portfolio))
            return ;
        //remove the account
        portfolios.remove(portfolio);
        //remove myself from the twitter account
        portfolio.setBrokerSubAccounts(null);
    }

    public Portfolio getPortfolio(Long portfolioId) {
        return this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(PortfolioNotFoundException::new);
    }
}
