package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.PortfolioNotFoundException;
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
//@EqualsAndHashCode(of = {"ticker"})
@NoArgsConstructor
@Getter
@Setter
public class Asset {

    public Asset(String ticker, String price){
        this.ticker = ticker;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_ID")
    private Long id;


    @Column(name = "ticker", unique = true)
    private String ticker;

    @Column(name = "price")
    private String price;

    @ManyToMany(mappedBy = "assets")
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
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with ID " + portfolioId + " not found"));
    }

    // TODO REFACTOR!!!
    public void setNullPortfolioById(Long portfolioId){
        Portfolio portfolioForChange = this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with ID " + portfolioId + " not found"));
        portfolioForChange = null;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Asset))
            return false;

        Asset other = (Asset)o;

        if (ticker == other.getTicker()) return true;
        if (ticker == null) return false;

        // equivalence by title
        return ticker.equals(other.getTicker());
    }

    public int hashCode() {
        if (ticker != null) {
            return ticker.hashCode();
        } else {
            return super.hashCode();
        }
    }
}
