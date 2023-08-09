package com.test.accounts.service.client;

import java.util.List;

import com.test.accounts.dtos.Customer;
import com.test.accounts.models.Cards;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("CARDS")
public interface CardsFeignClient {
    @RequestMapping(method = RequestMethod.POST,value = "myCards",consumes = "application/json")
    List<Cards>  getCardsByCustomerId(@RequestBody Customer customer);
}
