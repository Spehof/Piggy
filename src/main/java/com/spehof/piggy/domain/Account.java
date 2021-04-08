package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 08/04/2021
 */
@Entity
@Table(name = "accounts")
@Data
@EqualsAndHashCode(of = {"id"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

//    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    Client client;
}
