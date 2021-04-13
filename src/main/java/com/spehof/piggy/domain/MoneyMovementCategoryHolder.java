package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Table(name = "money_movement_categories")
@Data
@EqualsAndHashCode(of = {"id", "name"})
@NoArgsConstructor
public class MoneyMovementCategoryHolder extends BaseEntity {

    public MoneyMovementCategoryHolder(Client client){
        this.client = client;
    }

    @Id
    @Column(name = "id_client")
    Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    Client client;

// TODO add and remove categories
    @OneToMany()
    List<CostCategory> costCategories = new ArrayList<>();

// TODO add and remove categories
    @OneToMany()
    List<EarningCategory> earningCategories = new ArrayList<>();

    public void setCostCategories(List<CostCategory> costCategories) {
        for (CostCategory costCategory : costCategories) {
            this.setCostCategory(costCategory);
        }

    }

    public void setCostCategory(CostCategory costCategory) {
        //prevent endless loop
        if (this.costCategories.contains(costCategory))
            return ;
        //add new earning
        this.costCategories.add(costCategory);
        //set myself into the cost account
        costCategory.setMoneyMovementCategoryHolder(this);
    }

    public void setEarningCategories(List<EarningCategory> earningCategories) {
        for (EarningCategory earningCategory : earningCategories) {
            this.setEarningCategory(earningCategory);
        }

    }

    public void setEarningCategory(EarningCategory earningCategory) {
        //prevent endless loop
        if (this.earningCategories.contains(earningCategory))
            return ;
        //add new earning
        this.earningCategories.add(earningCategory);
        //set myself into the cost account
        earningCategory.setMoneyMovementCategoryHolder(this);
    }

    public void removeCostCategory(CostCategory costCategory) {
        //prevent endless loop
        if (!costCategories.contains(costCategory))
            return ;
        //remove the account
        costCategories.remove(costCategory);
        // myself from the twitter account
        costCategory.setMoneyMovementCategoryHolder(null);
    }

    public void removeEarningCategory(EarningCategory earningCategory) {
        //prevent endless loop
        if (!earningCategories.contains(earningCategory))
            return ;
        //remove the account
        earningCategories.remove(earningCategory);
        // myself from the twitter account
        earningCategory.setMoneyMovementCategoryHolder(null);
    }
}
