package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;

/**
 * @author Spehof
 * @created 09/04/2021
 */
@Entity
@Table(name = "goals")
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Goal extends BaseEntity {

    public Goal(Client client, Long amount, String title){
        this.client = client;
        this.amount = amount;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    @JsonIgnore
    private Client client;

    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    Long amount;

    public void setClient(Client client) {
        //prevent endless loop
        if (this.client != null && sameAsFormer(this.client, client))
            return;
        // set new client account
        Client oldClient = this.client;
        this.client = client;
        //remove from the old client account
        if (oldClient!=null)
            oldClient.setAccount(null);
        //set myself into new client account
        if (client!=null)
            client.setGoals(Collections.singletonList(this));
    }
}
