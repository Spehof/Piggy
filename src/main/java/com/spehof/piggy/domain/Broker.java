package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 20/04/2021
 */
@Entity
@Table(name = "brokers")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Broker extends BaseEntity {

    public Broker(Account account, String brokerType){
        this.account = account;
        this.brokerType = brokerType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_broker")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_account")
    @JsonIgnore
    Account account;

//    BrokerType brokerType;
//    BrokerSubaccount
    private String brokerType;
    private String brokerSubaccount;


    public void setAccount(Account account) {
        //prevent endless loop
        if (sameAsFormer(this.account, account))
            return ;
        //set new owner
        Account oldAccount = this.account;
        this.account = account;
        //remove from the old owner
        if (oldAccount!=null)
            oldAccount.removeBroker(this);
        //set myself into new owner
        if (account!=null)
            account.setBrokers(Collections.singletonList(this));
    }
}
