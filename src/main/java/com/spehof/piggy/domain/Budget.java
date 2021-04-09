package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */

@Entity
@Data
@Table(name = "budgets")
@EqualsAndHashCode(of = {"id", "value"})
public class Budget {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "client_id")
    Client client;


    Long value;
}
