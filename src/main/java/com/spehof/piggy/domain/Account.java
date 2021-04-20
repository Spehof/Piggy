package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.exception.BrokerNotFoundException;
import com.spehof.piggy.exception.CostNotFoundException;
import com.spehof.piggy.exception.EarningNotFoundException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@Entity
@Table(name = "accounts")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Account extends BaseEntity {

    public Account(Integer currency){
        this.currency = currency;
    }

    @Id
    @Column(name = "id_client")
    Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    @JsonManagedReference(value = "client-account")
    Client client;

    @OneToMany()
    private List<Earning> earnings = new ArrayList<>();

    @OneToMany()
    private List<Cost> costs = new ArrayList<>();

    @OneToMany()
    private List<Broker> brokers = new ArrayList<>();

    Integer currency;

    public void setCurrency(Integer currency) {
//        if (this.currency.equals(currency))
//            return ;
        this.currency = currency;
    }

    public void setClient(Client client) {
        //prevent endless loop
        if (this.client != null && sameAsFormer(this.client, client))
            return;
//        set new client account
        Client oldClient = this.client;
        this.client = client;
        //remove from the old client account
        if (oldClient!=null)
            oldClient.setAccount(null);
        //set myself into new client account
        if (client!=null)
            client.setAccount(this);
    }

    public void setCosts(List<Cost> costs){
        for (Cost cost : costs) {
            this.setCost(cost);
        }
    }

    public void setEarnings(List<Earning> earnings){
        for (Earning earning : earnings) {
            this.setEarning(earning);
        }
    }

    public void setCost(Cost cost) {
        //prevent endless loop
        if (this.costs.contains(cost))
            return ;
        //add new cost
        this.costs.add(cost);
        //set myself into the cost account
        cost.setAccount(this);
    }


    public void setEarning(Earning earning) {
        //prevent endless loop
        if (this.earnings.contains(earning))
            return ;
        //add new earning
        this.earnings.add(earning);
        //set myself into the cost account
        earning.setAccount(this);
    }

    public void removeEarning(Earning earning) {
        //prevent endless loop
        if (!earnings.contains(earning))
            return ;
        //remove the account
        earnings.remove(earning);
        // myself from the twitter account
        earning.setAccount(null);
    }

    public void removeCost(Cost cost) {
        //prevent endless loop
        if (!costs.contains(cost))
            return ;
        //remove the account
        costs.remove(cost);
        //remove myself from the twitter account
        cost.setAccount(null);
    }

    public void removeBroker(Broker broker) {
        //prevent endless loop
        if (!brokers.contains(broker))
            return ;
        //remove the account
        brokers.remove(broker);
        //remove myself from the twitter account
        broker.setAccount(null);
    }

    public void setBroker(Broker broker) {
        //prevent endless loop
        if (this.brokers.contains(broker))
            return ;
        //add new earning
        this.brokers.add(broker);
        //set myself into the cost account
        broker.setAccount(this);
    }


    public Earning getEarning(Long oldEarningId) {
        return this.earnings.stream()
                .filter(earning -> earning.id.equals(oldEarningId))
                .findFirst()
                .orElseThrow(EarningNotFoundException::new);
    }

    public Cost getCost(Long oldCostId) {
        return this.costs.stream()
                .filter(cost -> cost.id.equals(oldCostId))
                .findFirst()
                .orElseThrow(CostNotFoundException::new);
    }

    public Broker getBroker(Long id) {
        return this.brokers.stream()
                .filter(broker -> broker.getId().equals(id))
                .findFirst()
                .orElseThrow(BrokerNotFoundException::new);
    }
}
