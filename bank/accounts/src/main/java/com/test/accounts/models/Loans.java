package com.test.accounts.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class Loans {
    @Column(name="loan_number")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long loanNumber;
    @Column(name="customer_id")
    private int customerId;
    @Column(name="start_dt")
    private Date startDt;
    @Column(name="loan_type")
    private String loanType;

    @Column(name="total_loan")
    private int totalLoan;
    @Column(name="amount_paid")
    private int amountPaid;
    @Column(name="outstanding_amount")
    private int outstandingAmount;
    @Column(name="created_dt")
    private String createdDt;
}
