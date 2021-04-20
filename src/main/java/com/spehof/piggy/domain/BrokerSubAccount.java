package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Entity
@Table(name = "broker_sub_accounts")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class BrokerSubAccount extends BaseEntity {

    public BrokerSubAccount(Broker broker, String title){
        this.broker = broker;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_broker_sub_account")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_broker")
    @JsonIgnore
    Broker broker;

    String title;
}
