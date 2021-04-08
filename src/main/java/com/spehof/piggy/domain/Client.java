package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.utils.ClientViews;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spehof
 * @created 07/04/2021
 */

@Entity
@Table(name = "clients")
@Data
@EqualsAndHashCode(of = {"id", "name", "registrationDate"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ClientViews.IdName.class)
    private Long id;

    @Column(name = "name")
    @JsonView(ClientViews.IdName.class)
    private String name;

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonView(ClientViews.IdNameCreationdate.class)
    private LocalDateTime registrationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    /**
     * @JoinColumn
     * referencedColumnName = "account_id" - need to be @Column(name = "account_id") in class account
     * */
    @JoinColumn(name = "id")
    @JsonBackReference
    private Account account;

    @OneToMany()
    private List<MoneyMovementCategory> moneyMovementCategories = new ArrayList<>();

    @OneToMany()
    private List<MoneyHolderType> moneyHolderTypes = new ArrayList<>();

    @OneToMany()
    private List<Friend> friends = new ArrayList<>();

    @OneToMany()
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany()
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany()
    private List<Goal> goals = new ArrayList<>();



//    private List<Notification> notifications;
//    private List<Friend> friends;
//    private List<Goal> goals;
//    private List<Budget> budgets;
//    private List<MoneyMovementCategory> moneyMovementCategories;
//    private List<MoneyHolderType> moneyHolderTypes;


}
