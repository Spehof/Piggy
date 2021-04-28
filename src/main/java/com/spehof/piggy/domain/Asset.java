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
//@EqualsAndHashCode(of = {"title"})
@NoArgsConstructor
@Getter
@Setter
public class Asset {

    public Asset(String title, String price){
        this.title = title;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asset_ID")
    private Long id;


    @Column(name = "title", unique = true)
    private String title;

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

        if (title == other.getTitle()) return true;
        if (title == null) return false;

        // equivalence by title
        return title.equals(other.getTitle());
    }

    public int hashCode() {
        if (title != null) {
            return title.hashCode();
        } else {
            return super.hashCode();
        }
    }
}
