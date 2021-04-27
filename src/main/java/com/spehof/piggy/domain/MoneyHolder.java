package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Entity
@Table(name = "money_holders")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class MoneyHolder extends BaseEntity {

    public MoneyHolder(Client client, String title){
        this.client = client;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_holder_ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne()
    @JoinColumn(name = "client_ID", referencedColumnName = "client_ID")
    @JsonIgnore()
    private Client client;

}
