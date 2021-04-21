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
@Table(name = "trades")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Trade extends BaseEntity {

    public Trade(BrokerSubAccount brokerSubAccountWhichTrade,
                 Asset tradesAsset,
                 Long amount){
        this.brokerSubAccountWhichTrade = brokerSubAccountWhichTrade;
        this.tradesAsset = tradesAsset;
        this.amount = amount;
    }

    public Trade(BrokerSubAccount brokerSubAccountWhichTrade,
                 Asset tradesAsset,
                 Long amount,
                 Portfolio portfolioWhichTrade){
        this.brokerSubAccountWhichTrade = brokerSubAccountWhichTrade;
        this.tradesAsset = tradesAsset;
        this.amount = amount;
        this.portfolioWhichTrade = portfolioWhichTrade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trade")
    private Long id;

    @JoinColumn(name="id_trades_asset", referencedColumnName="id_asset")
    @OneToOne
    private Asset tradesAsset;

    Long amount;

    @ManyToOne()
    @JoinColumn(name = "id_broker_sub_account_which_trade")
    @JsonIgnore
    private BrokerSubAccount brokerSubAccountWhichTrade;

    @JoinColumn(name="id_portfolio_which_trade", referencedColumnName="id_portfolio")
    @OneToOne
    private Portfolio portfolioWhichTrade;

}
