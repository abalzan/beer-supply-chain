package com.andrei.beerservice.events;

import com.andrei.beerservice.web.model.BeerDto;

public class InventoryEvent extends BeerEvent {

    InventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
