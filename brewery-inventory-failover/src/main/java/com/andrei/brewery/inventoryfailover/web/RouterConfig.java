package com.andrei.brewery.inventoryfailover.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction inventoryRoute(InventoryHandler inventoryHandler) {
        return RouterFunctions.route(RequestPredicates.GET("inventory-failover")
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), inventoryHandler::listInventory);
    }

}
