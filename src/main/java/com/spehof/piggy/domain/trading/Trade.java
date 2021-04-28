package com.spehof.piggy.domain.trading;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.domain.BaseEntity;
import com.spehof.piggy.domain.trading.Asset;
import com.spehof.piggy.domain.trading.BrokerAccount;
import com.spehof.piggy.domain.trading.Portfolio;
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
@Table(name = "trades")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Trade extends BaseEntity {

    public Trade(BrokerAccount brokerAccountWhichTrade,
                 Asset tradesAsset,
                 Long amount){
        this.brokerAccountWhichTrade = brokerAccountWhichTrade;
        this.tradesAsset = tradesAsset;
        this.amount = amount;
    }

    public Trade(BrokerAccount brokerAccountWhichTrade,
                 Asset tradesAsset,
                 Long amount,
                 Portfolio portfolioWhichTrade){
        this.brokerAccountWhichTrade = brokerAccountWhichTrade;
        this.tradesAsset = tradesAsset;
        this.amount = amount;
        this.portfolioWhichTrade = portfolioWhichTrade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name="trades_asset_ID", referencedColumnName="asset_ID")
    private Asset tradesAsset;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne()
    @JoinColumn(name = "broker_account_ID")
    @JsonIgnore
    private BrokerAccount brokerAccountWhichTrade;

    @OneToOne
    @JoinColumn(name="portfolio_ID", referencedColumnName="portfolio_ID")
    private Portfolio portfolioWhichTrade;

}
