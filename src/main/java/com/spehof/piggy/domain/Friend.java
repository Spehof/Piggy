package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spehof.piggy.exception.LoanNotFoundException;
import com.spehof.piggy.exception.OweNotFoundException;
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
@Data
@Table(name = "friends")
@EqualsAndHashCode(of = {"id", "name"})
@NoArgsConstructor
public class Friend extends BaseEntity {

    public Friend(Client client, String name){
        this.client = client;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_friend")
    Long id;

    String name;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    @JsonIgnore
    Client client;

    @OneToMany()
    List<Loan> loans = new  ArrayList<>();

    @OneToMany()
    List<Owe> owes = new ArrayList<>();

//TODO refactor setClient to base entity
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
            client.setFriends(Collections.singletonList(this));
    }

    public void setLoans(List<Loan> loans){
        for (Loan loan : loans) {
            this.setLoan(loan);
        }
    }

    public void setLoan(Loan loan) {
        //prevent endless loop
        if (this.loans.contains(loan))
            return ;
        //add new cost
        this.loans.add(loan);
        //set myself into the cost account
        loan.setFriend(this);
    }

    public void removeLoan(Loan loan) {
        //prevent endless loop
        if (!loans.contains(loan))
            return ;
        //remove the account
        loans.remove(loan);
        // myself from the twitter account
        loan.setFriend(null);
    }

    public void setOwes(List<Owe> owes){
        for (Owe owe : owes) {
            this.setOwe(owe);
        }
    }

    public void setOwe(Owe owe) {
        //prevent endless loop
        if (this.owes.contains(owe))
            return ;
        //add new cost
        this.owes.add(owe);
        //set myself into the cost account
        owe.setFriend(this);
    }

    public void removeOwe(Owe owe) {
        //prevent endless loop
        if (!owes.contains(owe))
            return ;
        //remove the account
        owes.remove(owe);
        // myself from the twitter account
        owe.setFriend(null);
    }

    public Loan getLoan(Long id) {
        return this.loans.stream()
                .filter(loan -> loan.getId().equals(id))
                .findFirst()
                .orElseThrow(LoanNotFoundException::new);
    }

    public Owe getOwe(Long id) {
        return this.owes.stream()
                .filter(owe -> owe.getId().equals(id))
                .findFirst()
                .orElseThrow(OweNotFoundException::new);
    }
}

