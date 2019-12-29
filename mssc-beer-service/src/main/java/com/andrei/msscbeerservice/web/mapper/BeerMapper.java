package com.andrei.msscbeerservice.web.mapper;

import com.andrei.msscbeerservice.domain.Beer;
import com.andrei.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto BeerToBeerDto(Beer beer);

    Beer BeerDtoToBeer(BeerDto beerDto);
}
