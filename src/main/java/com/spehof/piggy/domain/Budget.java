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
@Table(name = "friends")
@EqualsAndHashCode(of = {"id", "value"})
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long value;
}
