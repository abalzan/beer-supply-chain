package com.andrei.beer.service.web.mapper;

import com.andrei.beer.service.domain.Beer;
import com.andrei.beer.service.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
