package com.test.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpHeaders;

@Order(1)
@Component
public class CustomTraceFilter implements GlobalFilter {
    private static final Logger Logger= LoggerFactory.getLogger(CustomTraceFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders=exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)){
            Logger.debug("EasyBank-CorrelationId found in tracing filter: {}.",filterUtility.getCorrelationId(requestHeaders));
        }else{
            String correlationId=generateCorrelationId();
            exchange=filterUtility.setCorrelationId(exchange,correlationId);
            Logger.debug("EasyBank-CorrelationId generated in tracing filter: {}.",filterUtility.getCorrelationId(requestHeaders));
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders httpHeaders){
        if(filterUtility.getCorrelationId(httpHeaders)!=null){
            return true;
        }
        return false;
    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }
}
