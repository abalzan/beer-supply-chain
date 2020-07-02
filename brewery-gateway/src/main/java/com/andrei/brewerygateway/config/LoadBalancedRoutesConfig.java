package com.andrei.brewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("local-discovery")
@Configuration
public class LoadBalancedRoutesConfig {
    @Bean
    public RouteLocator loadBalancedRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUpc/*")
                        .uri("lb://beer-service") //lb means load balance, we are doing "load balance and putting the spring application name
                        .id("beer-service"))
                .route(predicateSpec -> predicateSpec.path("/api/v1/customers/**")
                        .uri("lb://beer-order-service")
                        .id("order-service"))
                .route(predicateSpec -> predicateSpec.path("/api/v1/beer/*/inventory")
                        .uri("lb://beer-inventory-service")
                        .id("inventory-service"))

                .build();
    }
}
