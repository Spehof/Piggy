package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "assets")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Asset {

    public Asset(String name, String price){
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @ManyToMany(mappedBy = "assets")
    @JoinTable(name = "portfolios_assets",
            joinColumns = @JoinColumn(name = "asset_ID"),
            inverseJoinColumns = @JoinColumn(name = "portfolio_ID"))
    @JsonIgnore
    private List<Portfolio> portfolios = new ArrayList<>();

    public void setPortfolio(Portfolio portfolio) {
        if (portfolio == null) {

        }
        //prevent endless loop
        if (this.portfolios.contains(portfolio))
            return ;
        //add new earning
        this.portfolios.add(portfolio);
        //set myself into the cost account
        portfolio.setAsset(this);
    }

    public void removePortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (!portfolios.contains(portfolio))
            return ;
        //remove the account
        portfolios.remove(portfolio);
        //remove myself from the twitter account
        portfolio.setNullAssetById(this.getId());
    }

    public Portfolio getPortfolio(Long portfolioId) {
        return this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(PortfolioNotFoundException::new);
    }

    // TODO REFACTOR!!!
    public void setNullPortfolioById(Long portfolioId){
        Portfolio portfolioForChange = this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(PortfolioNotFoundException::new);
        portfolioForChange = null;
    }
}
