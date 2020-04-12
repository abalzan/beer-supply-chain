package com.andrei.beerservice.services;

import com.andrei.brewery.model.BeerOrderDto;

public interface AllocationService {
    Boolean allocatedOrder(BeerOrderDto beerOrderDto);
}
