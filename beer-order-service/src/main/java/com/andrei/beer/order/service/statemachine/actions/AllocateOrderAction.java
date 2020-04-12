package com.andrei.beer.order.service.statemachine.actions;

import com.andrei.beer.order.service.config.JmsConfig;
import com.andrei.beer.order.service.domain.BeerOrder;
import com.andrei.beer.order.service.domain.BeerOrderEventEnum;
import com.andrei.beer.order.service.domain.BeerOrderStatusEnum;
import com.andrei.beer.order.service.repositories.BeerOrderRepository;
import com.andrei.beer.order.service.services.BeerOrderManagerImpl;
import com.andrei.beer.order.service.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository repository;
    private final BeerOrderMapper mapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = repository.getOne(UUID.fromString(beerOrderId));
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, mapper.beerOrderToDto(beerOrder));

        log.debug("Sent allocation request for order id: " + beerOrderId);

    }
}
