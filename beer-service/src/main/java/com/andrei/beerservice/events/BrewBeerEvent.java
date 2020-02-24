package com.andrei.beerservice.events;

import com.andrei.beerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
