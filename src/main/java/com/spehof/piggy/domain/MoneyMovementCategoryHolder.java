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

// TODO for draft
//    public void setClient(Client client) {
//        //prevent endless loop
//        if (this.client != null && sameAsFormer(this.client, client))
//            return;
//        // set new client account
//        Client oldClient = this.client;
//        this.client = client;
//        //remove from the old client account
//        if (oldClient!=null)
//            oldClient.setAccount(null);
//        //set myself into new client account
//        if (client!=null)
//            client.setMoneyMovementCategories(Collections.singletonList(this));
//    }
}
