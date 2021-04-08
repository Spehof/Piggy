package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */@Entity
@Data
@Table(name = "money_holder_type")
@EqualsAndHashCode(of = {"id", "name"})
public class MoneyHolderType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
