package com.andrei.beerservice.service.order;

import com.andrei.beerservice.config.JmsConfig;
import com.andrei.brewery.model.events.ValidateOrderRequest;
import com.andrei.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeeroOrderValidator validator;
    private final JmsTemplate template;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = validator.validateOrder(validateOrderRequest.getBeerOrderDto());

        template.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateOrderResult.builder()
                        .isValid(isValid)
                        .orderId(validateOrderRequest.getBeerOrderDto().getId())
                        .build());
    }
}
