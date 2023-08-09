package com.test.accounts.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@ToString
public class Accounts {
    @Column(name="customer_id")
    private int customerId;
    @Column(name="account_number")
    @Id
    private long accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;
    @Column(name="created_dt")
    private LocalDate createdDt;
}
