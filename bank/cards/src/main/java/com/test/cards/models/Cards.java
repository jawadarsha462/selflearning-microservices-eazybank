package com.test.cards.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Cards {
    @Column(name="card_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long cardId;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name="customer_id")
    private int customerId;
    @Column(name="card_type")
    private String cardType;

    @Column(name="total_limit")
    private int totalLimit;
    @Column(name="amount_used")
    private int amountUsed;
    @Column(name="available_amount")
    private int availableAmount;
    @Column(name="created_dt")
    private String createdDt;
}
