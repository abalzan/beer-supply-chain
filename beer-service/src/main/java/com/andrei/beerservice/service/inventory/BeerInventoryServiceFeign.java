package com.andrei.beerservice.service.inventory;

import com.andrei.beerservice.service.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Profile("local-discovery")
@Service
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final BeerInventoryServiceFeignClient beerInventoryServiceFeignClient;

    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.debug("Calling Inventory Service BeerID: " + beerId);

        ResponseEntity<List<BeerInventoryDto>> responseEntity = beerInventoryServiceFeignClient.getOnHandInventory(beerId);

        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        log.debug("BeerID: " + beerId + " OnHand is:" + onHand);

        return onHand;
    }
}
