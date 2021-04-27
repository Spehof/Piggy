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
    @Column(name = "transaction_ID")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "account_ID")
    @JsonIgnore
    private Account account;


    @OneToOne
    @JoinColumn(name = "from_money_holder_ID", referencedColumnName="money_holder_ID")
    private MoneyHolder fromMoneyHolderId;


    @OneToOne
    @JoinColumn(name="to_money_holder_ID", referencedColumnName="money_holder_ID")
    private MoneyHolder toMoneyHolderId;

    @Column(name = "amount")
    private BigDecimal amount;
}
