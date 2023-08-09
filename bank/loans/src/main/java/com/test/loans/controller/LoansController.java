package com.test.loans.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.test.loans.config.LoansServiceConfig;
import com.test.loans.config.Properties;
import com.test.loans.dtos.Customer;
import com.test.loans.models.Loans;
import com.test.loans.repositories.LoansRepository;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoansController {
    private static final org.slf4j.Logger Logger= LoggerFactory.getLogger(LoansController.class);
    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansServiceConfig;

    @PostMapping("/myLoans")
    @ResponseBody
    public List<Loans> getLoanDetails(@RequestBody Customer customer){
        Logger.info("getLoanDetails() method started");
        List<Loans> loans=loansRepository.findByCustomerIdOrderByStartDtAsc(customer.getCustomerId());
        if(loans!=null){
            Logger.info("getLoanDetails() method ended");
            return loans;
        }else{
            return null;
        }
    }

    @GetMapping("/loans/properties")
    @SneakyThrows
    public String getPropertyDetails(){
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties=new Properties(loansServiceConfig.getMsg(),loansServiceConfig.getBuildVersion());
        return ow.writeValueAsString(properties);
    }
}
