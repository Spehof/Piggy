package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 13/04/2021
 */
@Entity
@Table(name = "earning_categories")
@Data
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
public class EarningCategory extends BaseEntity {

    public EarningCategory(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String earningCategoryName){
        this.moneyMovementCategoryHolder = moneyMovementCategoryHolder;
        this.earningCategoryName = earningCategoryName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_earning_category")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_money_movement_category")
    @JsonIgnore
    private MoneyMovementCategoryHolder moneyMovementCategoryHolder;

    private String earningCategoryName;

    //    TODO add setters and getters
}