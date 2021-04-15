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
@Table(name = "notifications")
@Data
// TODO add creating time etc. and add here
@EqualsAndHashCode(of = {"message"})
@NoArgsConstructor
public class Notification extends BaseEntity {

    public Notification(Client client, String message){
        this.client = client;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    Long id;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    @JsonIgnore
    Client client;

    String message;

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
            client.setNotifications(Collections.singletonList(this));
    }
}
