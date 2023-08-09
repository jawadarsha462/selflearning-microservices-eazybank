package com.test.cards.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.test.cards.config.CardsServiceConfig;
import com.test.cards.config.Properties;
import com.test.cards.models.Cards;
import com.test.loans.dtos.Customer;
import com.test.cards.repositories.CardsRepository;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsController {
    private static final org.slf4j.Logger Logger= LoggerFactory.getLogger(CardsController.class);
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsServiceConfig;

    @PostMapping("/myCards")
    @ResponseBody
    public List<Cards> getLoanDetails(@RequestBody Customer customer){
        Logger.info("getLoanDetails() method started");
        List<Cards> cards=cardsRepository.findByCustomerId(customer.getCustomerId());
        if(cards!=null){
            Logger.info("getLoanDetails() method ended");
            return cards;
        }else{
            return null;
        }
    }

    @GetMapping("/cards/properties")
    @SneakyThrows
    public String getPropertyDetails(){
        ObjectWriter ow=new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties=new Properties(cardsServiceConfig.getMsg(),cardsServiceConfig.getBuildVersion());
        return ow.writeValueAsString(properties);
    }
}
