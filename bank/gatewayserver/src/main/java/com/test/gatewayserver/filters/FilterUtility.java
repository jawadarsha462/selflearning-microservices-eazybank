package com.test.gatewayserver.filters;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
    public static final String CORRELATION_ID="eazybank-correlation-id";

    public String getCorrelationId(HttpHeaders httpHeaders){
        if(httpHeaders.get(CORRELATION_ID)!=null){
            List<String> requestHeaderList=httpHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        }
        return null;
    }


    public ServerWebExchange setCorrelationId(ServerWebExchange exchange,String correlationId){
        return exchange.mutate().request(exchange.getRequest().mutate().header(CORRELATION_ID,correlationId).build()).build();
    }
}
