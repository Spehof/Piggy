package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */

@Entity
@Data
@Table(name = "budgets")
@EqualsAndHashCode(of = {"id", "value"})
public class Budget extends BaseEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "client_id")
    Client client;


    Long value;

    public void setClient(Client client) {
        //prevent endless loop
        if (sameAsFormer(this.client, client))
            return ;
        //set new owner
        Client oldClient = this.client;
        this.client = client;
        //remove from the old owner
        if (oldClient!=null)
            oldClient.removeTwitterAccount(this);
        //set myself into new owner
        if (owner!=null)
            owner.addTwitterAccount(this);
    }
    }
}
