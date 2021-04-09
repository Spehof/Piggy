package com.spehof.piggy.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Table(name = "notifications")
@Data
@EqualsAndHashCode(of = {"id", "message"})
public class Notification {

    @Id
    @Column(name = "client_id")
    Long id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "client_id")
    Client client;

    String message;
}
