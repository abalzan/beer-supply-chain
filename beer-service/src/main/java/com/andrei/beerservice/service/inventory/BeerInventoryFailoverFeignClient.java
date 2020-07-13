package com.andrei.beerservice.service.inventory;

import com.andrei.beerservice.service.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("inventory-failover")
public interface BeerInventoryFailoverFeignClient {

    @GetMapping(value = "/inventory-failover")
    ResponseEntity<List<BeerInventoryDto>> getOnHandInventory();

}
