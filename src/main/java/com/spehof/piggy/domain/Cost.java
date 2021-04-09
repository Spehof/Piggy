package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Table(name = "costs")
@Data
@EqualsAndHashCode(of = {"id", "amount"})
public class Cost {

    @Id
    @Column(name = "account_id")
    Long id;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "account_id")
    Client client;

    Long amount;
}
