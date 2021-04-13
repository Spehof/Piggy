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
@Table(name = "cost_categories")
@Data
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
public class CostCategory extends BaseEntity {

    public CostCategory(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String name){
        this.moneyMovementCategoryHolder = moneyMovementCategoryHolder;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cost_category")
    Long id;

    @ManyToOne
    @JoinColumn(name = "id_money_movement_category")
    @JsonIgnore
    private MoneyMovementCategoryHolder moneyMovementCategoryHolder;

    private String name;
}
