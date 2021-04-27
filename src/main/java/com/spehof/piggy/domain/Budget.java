package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 09/04/2021
 */

@Entity
@Data
@Table(name = "budgets")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Budget extends BaseEntity {

    public Budget(Client client, Long value){
        this.client = client;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_ID")
    @JsonIgnore
    private Client client;

    @Column(name = "value")
    private Long value;

    public void setClient(Client client) {
        //prevent endless loop
        if (sameAsFormer(this.client, client))
            return ;
        //set new owner
        Client oldClient = this.client;
        this.client = client;
        //remove from the old owner
        if (oldClient!=null)
            oldClient.removeBudget(this);
        //set myself into new owner
        if (client!=null)
            client.setBudgets(Collections.singletonList(this));
    }

}
