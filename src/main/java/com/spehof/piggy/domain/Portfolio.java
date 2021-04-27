package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.AssetNotFoundException;
import com.spehof.piggy.exception.BrokerAccountNotFoundException;
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
public class Portfolio extends BaseEntity {

    public Portfolio(Client client, String title){
        this.client = client;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    @JsonIgnore
    private Client client;

    @ManyToMany(mappedBy = "portfolios")
    @JoinTable(name = "portfolios_broker_accounts", joinColumns =
        @JoinColumn(name = "portfolio_ID"), inverseJoinColumns = @JoinColumn(name = "broker_account_ID"))
    @JsonIgnore
    private List<BrokerAccount> brokerAccounts = new ArrayList<>();

    @ManyToMany(mappedBy = "portfolios")
    @JoinTable(name = "portfolios_assets", joinColumns =
        @JoinColumn(name = "portfolio_ID"), inverseJoinColumns = @JoinColumn(name = "asset_ID"))
    private List<Asset> assets = new ArrayList<>();

    @Column(name = "title")
    private String title;

    public void setBrokerSubAccount(BrokerAccount brokerAccount) {
        //prevent endless loop
        if (this.brokerAccounts.contains(brokerAccount))
            return ;
        //add new earning
        this.brokerAccounts.add(brokerAccount);
        //set myself into the cost account
        brokerAccount.setPortfolio(this);
    }

    public void removeBrokerSubAccount(BrokerAccount brokerAccount) {
        //prevent endless loop
        if (!brokerAccounts.contains(brokerAccount))
            return ;
        //remove the account
        brokerAccounts.remove(brokerAccount);
        //remove myself from the twitter account
        brokerAccount.setPortfolio(null);
    }

    public BrokerAccount getBrokerSubAccount(Long brokerSubAccountId) {
        return this.brokerAccounts.stream()
                .filter(brokerSubAccount -> brokerSubAccount.getId().equals(brokerSubAccountId))
                .findFirst()
                .orElseThrow(BrokerAccountNotFoundException::new);
    }

    public void setAsset(Asset asset) {
        //prevent endless loop
        if (this.assets.contains(asset))
            return ;
        //add new earning
        this.assets.add(asset);
        //set myself into the cost account
        asset.setPortfolio(this);
    }

    public void removeAsset(Asset asset) {
        //prevent endless loop
        if (!assets.contains(asset))
            return ;
        //remove the account
        assets.remove(asset);
        //remove myself from the twitter account
        asset.setNullPortfolioById(this.getId());
    }

    public void setNullAssetById(Long assetId){
        Asset assetForChange = this.assets.stream()
                .filter(asset -> asset.getId().equals(assetId))
                .findFirst()
                .orElseThrow(AssetNotFoundException::new);
        assetForChange = null;
    }

    public Asset getAsset(Long assetId) {
        return this.assets.stream()
                .filter(asset -> asset.getId().equals(assetId))
                .findFirst()
                .orElseThrow(AssetNotFoundException::new);
    }
}
