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
}

