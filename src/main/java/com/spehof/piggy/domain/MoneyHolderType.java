package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 09/04/2021
 */@Entity
@Data
@Table(name = "money_holder_type")
@EqualsAndHashCode(of = {"id", "name"})
public class MoneyHolderType extends BaseEntity {

    @Id
    @Column(name = "client_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    Client client;

    String name;

    public void setClient(Client client) {
        //prevent endless loop
        if (this.client != null && sameAsFormer(this.client, client))
            return;
        // set new client account
        Client oldClient = this.client;
        this.client = client;
        //remove from the old client account
        if (oldClient!=null)
            oldClient.setAccount(null);
        //set myself into new client account
        if (client!=null)
            client.setMoneyHolderTypes(Collections.singletonList(this));
    }
}
