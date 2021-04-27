package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.exception.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_ID")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    @JsonManagedReference(value = "client-account")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Earning> earnings = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cost> costs = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Broker> brokers = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @Column(name = "currency")
    private Integer currency;

    public void setCurrency(Integer currency) {
//        if (this.currency.equals(currency))
//            return ;
        this.currency = currency;
    }

    public void setUser(User user) {
        //prevent endless loop
        if (this.user != null && sameAsFormer(this.user, user))
            return;
//        set new client account
        User oldUser = this.user;
        this.user = user;
        //remove from the old client account
        if (oldUser !=null)
            oldUser.setAccount(null);
        //set myself into new client account
        if (user !=null)
            user.setAccount(this);
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
        cost.setAccount(this);
    }


    public void setEarning(Earning earning) {
        //prevent endless loop
        if (this.earnings.contains(earning))
            return ;
        //add new earning
        this.earnings.add(earning);
        earning.setAccount(this);
    }

    public void setTransaction(Transaction transaction) {
        //prevent endless loop
        if (this.transactions.contains(transaction))
            return ;
        //add new cost
        this.transactions.add(transaction);
        transaction.setAccount(this);
    }

    public void removeEarning(Earning earning) {
        //prevent endless loop
        if (!earnings.contains(earning))
            return ;
        //remove the account
        earnings.remove(earning);
        earning.setAccount(null);
    }

    public void removeTransaction(Transaction transaction) {
        //prevent endless loop
        if (!transactions.contains(transaction))
            return ;
        //remove the account
        transactions.remove(transaction);
        transaction.setAccount(null);
    }

    public void removeCost(Cost cost) {
        //prevent endless loop
        if (!costs.contains(cost))
            return ;
        //remove the account
        costs.remove(cost);
        cost.setAccount(null);
    }

    public void removeBroker(Broker broker) {
        //prevent endless loop
        if (!brokers.contains(broker))
            return ;
        //remove the account
        brokers.remove(broker);
        broker.setAccount(null);
    }

    public void setBroker(Broker broker) {
        //prevent endless loop
        if (this.brokers.contains(broker))
            return ;
        //add new earning
        this.brokers.add(broker);
        broker.setAccount(this);
    }


    public Earning getEarning(Long earningId) {
        return this.earnings.stream()
                .filter(earning -> earning.getId().equals(earningId))
                .findFirst()
                .orElseThrow(() -> new EarningNotFoundException("Earning category with ID " + earningId + " not found"));
    }

    public Transaction getTransaction(Long transactionId) {
        return this.transactions.stream()
                .filter(transaction -> transaction.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(TransactionNotFoundException::new);
    }

    public Cost getCost(Long costId) {
        return this.costs.stream()
                .filter(cost -> cost.getId().equals(costId))
                .findFirst()
                .orElseThrow(() -> new CostNotFoundException("Cost with ID " + costId + " not found"));
    }

    public Broker getBroker(Long brokerId) {
        return this.brokers.stream()
                .filter(broker -> broker.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new BrokerNotFoundException("Broker with ID " + brokerId + " not found"));
    }
}
