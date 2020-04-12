package com.andrei.brewery.model.events;

import com.andrei.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocatedOrderRequest {

    private BeerOrderDto beerOrderDto;
}
