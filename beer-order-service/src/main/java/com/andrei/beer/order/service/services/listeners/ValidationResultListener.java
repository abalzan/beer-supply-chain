package com.andrei.beer.order.service.services.listeners;

import com.andrei.beer.order.service.config.JmsConfig;
import com.andrei.beer.order.service.services.BeerOrderManager;
import com.andrei.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {

    private final BeerOrderManager beerOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult validateOrderResult) {
        final UUID beerOrderId = validateOrderResult.getOrderId();

        log.debug("Validation Result fro order id: " + beerOrderId);

        beerOrderManager.processValidationResult(beerOrderId, validateOrderResult.getIsValid());
    }
}
