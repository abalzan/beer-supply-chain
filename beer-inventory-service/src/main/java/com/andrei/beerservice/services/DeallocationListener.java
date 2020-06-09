package com.andrei.beerservice.services;

import com.andrei.beerservice.config.JmsConfig;
import com.andrei.brewery.model.events.AllocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DeallocationListener {
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest request) {
        allocationService.deallocateOrder(request.getBeerOrderDto());
    }
}
