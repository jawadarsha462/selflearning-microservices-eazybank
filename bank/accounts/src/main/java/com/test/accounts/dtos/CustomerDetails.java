package com.test.accounts.dtos;

import com.test.accounts.models.Accounts;
import com.test.accounts.models.Cards;
import com.test.accounts.models.Loans;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CustomerDetails {
    private Accounts accounts;
    private List<Loans> loans;
    private List<Cards> cards;
}
