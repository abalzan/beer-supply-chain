package com.andrei.beerservice.service.inventory;

import com.andrei.beerservice.config.FeignClientConfig;
import com.andrei.beerservice.service.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "beer-inventory-service", fallback = BeerInventoryServiceFeignClientFailover.class, configuration = FeignClientConfig.class)
public interface BeerInventoryServiceFeignClient {

    @GetMapping(value = BeerInventoryServiceRestTemplate.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(@PathVariable UUID beerId);
}
