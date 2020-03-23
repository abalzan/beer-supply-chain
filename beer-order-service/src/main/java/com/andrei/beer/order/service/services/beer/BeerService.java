package com.andrei.beer.order.service.services.beer;

import com.andrei.brewery.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID beerId);

    Optional<BeerDto> getBeerByUpc(Long upc);
}
