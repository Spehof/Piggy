package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Spehof
 * @created 21/04/2021
 */
@Entity
@Table(name = "transactions")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@Getter
@Setter
public class Transaction extends BaseEntity{

    public Transaction(Account account,
                       MoneyHolder fromMoneyHolderId,
                       MoneyHolder toMoneyHolderId,
                       BigDecimal amount){
        this.account = account;
        this.fromMoneyHolderId = fromMoneyHolderId;
        this.toMoneyHolderId = toMoneyHolderId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_account")
    @JsonIgnore
    private Account account;


    @JoinColumn(name="from_money_holder_id", referencedColumnName="id_money_holder")
    @OneToOne
    private MoneyHolder fromMoneyHolderId;


    @JoinColumn(name="to_money_holder_id", referencedColumnName="id_money_holder")
    @OneToOne
    private MoneyHolder toMoneyHolderId;

    private BigDecimal amount;
}
