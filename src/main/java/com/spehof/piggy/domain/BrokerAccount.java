package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.PortfolioNotFoundException;
import com.spehof.piggy.exception.TradeNotFoundException;
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
@Table(name = "broker_accounts")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class BrokerAccount extends BaseEntity {

    public BrokerAccount(Broker broker, String title){
        this.broker = broker;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "broker_account_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "broker_ID", referencedColumnName = "broker_ID")
    @JsonIgnore
    private Broker broker;

    @ManyToMany(mappedBy = "brokerAccounts")
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    @OneToMany(mappedBy = "brokerAccountWhichTrade", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Trade> trades = new ArrayList<>();

    @Column(name = "title")
    private String title;

    public void setPortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (this.portfolios.contains(portfolio))
            return ;
        //add new earning
        this.portfolios.add(portfolio);
        //set myself into the cost account
        portfolio.setBrokerSubAccount(this);
    }

    public void setTrade(Trade trade) {
        //prevent endless loop
        if (this.trades.contains(trade))
            return ;
        //add new earning
        this.trades.add(trade);
        //set myself into the cost account
        trade.setBrokerAccountWhichTrade(this);
    }

    public void removePortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (!portfolios.contains(portfolio))
            return ;
        //remove the account
        portfolios.remove(portfolio);
        //remove myself from the twitter account
        portfolio.setBrokerAccounts(null);
    }

    public void removeTrade(Trade trade) {
        //prevent endless loop
        if (!trades.contains(trade))
            return ;
        //remove the account
        trades.remove(trade);
        //remove myself from the twitter account
        trade.setPortfolioWhichTrade(null);
    }

    public Trade getTrade(Long tradeId) {
        return this.trades.stream()
                .filter(trade -> trade.getId().equals(tradeId))
                .findFirst()
                .orElseThrow(() -> new TradeNotFoundException("Trade with ID " + tradeId + " not found"));
    }


    public Portfolio getPortfolio(Long portfolioId) {
        return this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with ID " + portfolioId + " not found"));
    }
}
