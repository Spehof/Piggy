package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.EarningNotFoundException;
import com.spehof.piggy.exception.MoneyHolderNotFoundException;
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
 */@Entity
@Data
@Table(name = "money_holder_type")
@EqualsAndHashCode(of = {"id"})
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

    @OneToMany
    List<MoneyHolder> moneyHolders = new ArrayList<>();

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

    public void setMoneyHolder(MoneyHolder moneyHolder) {
        //prevent endless loop
        if (this.moneyHolders.contains(moneyHolder))
            return ;
        //add new cost
        this.moneyHolders.add(moneyHolder);
        //set myself into the cost account
        moneyHolder.setMoneyHolderType(this);
    }

    public void removeMoneyHolder(MoneyHolder moneyHolder) {
        //prevent endless loop
        if (!moneyHolders.contains(moneyHolder))
            return ;
        //remove the account
        moneyHolders.remove(moneyHolder);
        //remove myself from the twitter account
        moneyHolder.setMoneyHolderType(null);
    }

    public MoneyHolder getMoneyHolder(Long moneyHolderId) {
        return this.moneyHolders.stream()
                .filter(moneyHolder -> moneyHolder.getId().equals(moneyHolderId))
                .findFirst()
                .orElseThrow(MoneyHolderNotFoundException::new);
    }
}
