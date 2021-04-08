package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.spehof.piggy.utils.ClientViews;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Spehof
 * @created 07/04/2021
 */

@Entity
@Table(name = "client")
@Data
@EqualsAndHashCode(of = {"id"})
//@AllArgsConstructor
//@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    @JsonView(ClientViews.IdName.class)
    private Long id;

    @Column(name = "name")
    @JsonView(ClientViews.IdName.class)
    private String name;

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonView(ClientViews.IdNameCreationdate.class)
    private LocalDateTime registrationDate;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL
    )
//    @JoinColumn(name = "account_id",referencedColumnName = "id")
    private Account account;




//    private List<Notification> notifications;
//    private List<Friend> friends;
//    private List<Goal> goals;
//    private List<Budget> budgets;
//    private List<MoneyMovementCategory> moneyMovementCategories;
//    private List<MoneyHolderType> moneyHolderTypes;


}
