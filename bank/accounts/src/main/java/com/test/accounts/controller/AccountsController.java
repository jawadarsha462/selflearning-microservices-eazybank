package com.test.accounts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.test.accounts.config.AccountsServiceConfig;
import com.test.accounts.config.Properties;
import com.test.accounts.dtos.Customer;
import com.test.accounts.dtos.CustomerDetails;
import com.test.accounts.models.Accounts;
import com.test.accounts.models.Cards;
import com.test.accounts.models.Loans;
import com.test.accounts.repositories.AccountsRepository;
import com.test.accounts.service.client.CardsFeignClient;
import com.test.accounts.service.client.LoansFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.micrometer.core.annotation.Timed;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AccountsController {
    private static final org.slf4j.Logger Logger= LoggerFactory.getLogger(AccountsController.class);
    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    AccountsServiceConfig accountsServiceConfig;

    @Autowired
    CardsFeignClient cardsFeignClient;

    @Autowired
    LoansFeignClient loansFeignClient;

    @PostMapping("/myAccount")
    @Timed(value = "getAccountDetails.time",description = "Time taken to return Account details")
    public Accounts getAccountDetails(@RequestBody Customer customer){
        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId());
        if(accounts!=null){
            return accounts;
        }else{
            return null;
        }
    }

    @GetMapping("/account/properties")
    @SneakyThrows
    public String getPropertyDetails(){
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties=new Properties(accountsServiceConfig.getMsg(),accountsServiceConfig.getBuildVersion());
        return ow.writeValueAsString(properties);
    }

    @PostMapping("/customerDetails")
    @CircuitBreaker(name="detailsForCustomerSupportApp",fallbackMethod = "customerDetailsFallback")
//    @Retry(name="retryForCustomerDetails",fallbackMethod = "customerDetailsFallback")
    public CustomerDetails getCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId,@RequestBody Customer customer){
        Logger.info("getCustomerDetails() method started");
        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId());
        List<Cards> cards=cardsFeignClient.getCardsByCustomerId(customer);
        List<Loans> loans=loansFeignClient.getLoansByCustomerId(customer);

        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setAccounts(accounts);
        customerDetails.setLoans(loans);
        customerDetails.setCards(cards);
        Logger.info("getCustomerDetails() method ended");
        return customerDetails;
    }

    private CustomerDetails customerDetailsFallback(@RequestHeader("eazybank-correlation-id") String correlationId,Customer customer,Throwable t){
        CustomerDetails customerDetails=new CustomerDetails();
        return customerDetails;
    }

    @GetMapping("/sayHello")
    @RateLimiter(name="sayHello",fallbackMethod = "sayHelloFallback")
    public String sayHello(){
        return "hi, welcome to easy bank";
    }

    private String sayHelloFallback(Throwable t){
        return "Hi, we are in fallback due to rate limiter";
    }
}
