package com.andrei.beerservice.service.order;

import com.andrei.beerservice.repository.BeerRepository;
import com.andrei.brewery.model.BeerOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeeroOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger beerNotFound = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            if (beerRepository.findByUpc(beerOrderLineDto.getUpc()) == null) {
                beerNotFound.incrementAndGet();
            }
        });
        return beerNotFound.get() == 0;
    }
}
