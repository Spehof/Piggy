package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spehof.piggy.exception.CostCategoryNotFoundException;
import com.spehof.piggy.exception.EarningCategoryNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Table(name = "money_movement_categories")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Setter
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
    @JsonManagedReference(value = "client-moneyMovementCategoryHolder")
    private Client client;


    @OneToMany()
    private Set<CostCategory> costCategories = new HashSet<>();


    @OneToMany()
    private Set<EarningCategory> earningCategories = new HashSet<>();


    public void setCostCategories(Set<CostCategory> costCategories) {
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

    public void setEarningCategories(Set<EarningCategory> earningCategories) {
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

    public Set<EarningCategory> getEarningCategories() {
        return earningCategories;
    }

    public Set<CostCategory> getCostCategories() {
        return costCategories;
    }

    public EarningCategory getEarningCategory(Long id) {
        return this.earningCategories.stream()
                .filter(earningCategory -> earningCategory.getId().equals(id))
                .findFirst()
                .orElseThrow(EarningCategoryNotFoundException::new);
    }

    public CostCategory getCostCategory(Long id) {
        return this.costCategories.stream()
                .filter(costCategory -> costCategory.getId().equals(id))
                .findFirst()
                .orElseThrow(CostCategoryNotFoundException::new);
    }
}
