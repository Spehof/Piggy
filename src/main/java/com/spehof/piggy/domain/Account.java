package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.utils.AccountViews;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "client_id")
    Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    Client client;

    @OneToMany()
    @PrimaryKeyJoinColumn
    private List<Earning> earnings = new ArrayList<>();

    Integer Currency;
}
