package com.andrei.beer.service.service;

import com.andrei.beer.service.web.model.BeerDto;
import com.andrei.beer.service.web.model.BeerPageList;
import com.andrei.beer.service.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerPageList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
