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
 */@Entity
@Data
@Table(name = "money_holder_type")
// TODO add creating time etc. and add here
@EqualsAndHashCode(of = {"name"})
@NoArgsConstructor
public class MoneyHolderType extends BaseEntity {

     public MoneyHolderType(Client client, String name){
         this.client = client;
         this.name = name;
     }

    @Id
    @Column(name = "id_money_holder_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    @JsonIgnore
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
