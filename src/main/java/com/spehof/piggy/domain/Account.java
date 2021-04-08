package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.utils.AccountViews;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer Currency;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    @JsonIgnore
    @JsonManagedReference
    Client client;
}
