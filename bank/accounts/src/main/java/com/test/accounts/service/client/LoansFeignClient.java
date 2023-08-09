package com.test.accounts.service.client;

import com.test.accounts.dtos.Customer;
import com.test.accounts.models.Loans;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("LOANS")
public interface LoansFeignClient {
    @RequestMapping(method = RequestMethod.POST,value = "myLoans",consumes = "application/json")
    List<Loans>  getLoansByCustomerId(@RequestBody Customer customer);
}
