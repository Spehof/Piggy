package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Data
@Table(name = "friends")
@EqualsAndHashCode(of = {"id", "name"})
public class Friend {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "client_id")
    Client client;

    @OneToMany()
    @PrimaryKeyJoinColumn
    List<Loan> loans = new  ArrayList<>();

    @OneToMany()
    @PrimaryKeyJoinColumn
    List<Owe> owes = new ArrayList<>();

    String name;

    public void setLoans(List<Loan> loans){
        for (Loan loan : loans) {
            this.setLoan(loan);
        }
    }

    private void setLoan(Loan loan) {
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

    private void setOwe(Owe owe) {
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
}

