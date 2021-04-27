package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 13/04/2021
 *
 * Class represents entity client cost category
 */
@Entity
@Table(name = "cost_categories")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class CostCategory extends BaseEntity {

    public CostCategory(MoneyMovementCategoryHolder moneyMovementCategoryHolder, String title){
        this.moneyMovementCategoryHolder = moneyMovementCategoryHolder;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_category_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "money_movement_category_holder_ID")
    @JsonIgnore
    private MoneyMovementCategoryHolder moneyMovementCategoryHolder;

    @Column(name = "title")
    private String title;
}
