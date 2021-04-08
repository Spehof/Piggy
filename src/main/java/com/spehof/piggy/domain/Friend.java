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
@EqualsAndHashCode(of = {"id", "loan", "owe"})
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Client client;

    @OneToMany()
    List<Loan> loans = new  ArrayList<>();

    @OneToMany()
    List<Owe> owes = new ArrayList<>();
}

