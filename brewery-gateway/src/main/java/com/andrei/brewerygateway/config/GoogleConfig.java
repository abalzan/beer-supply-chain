package com.andrei.brewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("google")
@Configuration
public class GoogleConfig {
    // redirect the url localhost:9090/googlesearch to open google!!
    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/googlesearch")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/googlesearch(?<segment>/?.*)", "/${segment}"))
                        .uri("http://google.com")
                        .id("google"))
                .build();
    }
}
