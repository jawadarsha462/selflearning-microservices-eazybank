package com.test.loans.repositories;

import java.util.List;
import com.test.loans.models.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoansRepository extends CrudRepository<Loans,Long> {
    List<Loans> findByCustomerIdOrderByStartDtAsc(int customerId);
}
