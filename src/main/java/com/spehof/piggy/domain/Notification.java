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
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Notification extends BaseEntity {

    public Notification(User user, String message){
        this.user = user;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    @JsonIgnore
    private User user;

    @Column(name = "message")
    private String message;

    public void setUser(User user) {
        //prevent endless loop
        if (this.user != null && sameAsFormer(this.user, user))
            return;
        // set new client account
        User oldUser = this.user;
        this.user = user;
        //remove from the old client account
        if (oldUser !=null)
            oldUser.setAccount(null);
        //set myself into new client account
        if (user !=null)
            user.setNotifications(Collections.singletonList(this));
    }
}
