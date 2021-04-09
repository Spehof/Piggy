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
@Table(name = "owes")
@EqualsAndHashCode(of = {"id"})
public class Owe {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    Long id;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "friend_id")
    Friend friend;

    Long amount;
}
