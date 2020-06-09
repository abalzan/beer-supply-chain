package com.andrei.beerservice.services;

import com.andrei.beerservice.domain.BeerInventory;
import com.andrei.beerservice.repositories.BeerInventoryRepository;
import com.andrei.brewery.model.BeerOrderDto;
import com.andrei.brewery.model.BeerOrderLineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final BeerInventoryRepository repository;

    @Override
    public Boolean allocatedOrder(BeerOrderDto beerOrderDto) {
        log.debug("Allocating order id: " + beerOrderDto.getId());

        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            if ((((beerOrderLineDto.getOrderQuantity() != null ? beerOrderLineDto.getOrderQuantity() : 0)
                    - (beerOrderLineDto.getQuantityAllocated() != null ? beerOrderLineDto.getQuantityAllocated() : 0)) > 0)) {
                allocateBeerOrderLine(beerOrderLineDto);
            }
            totalOrdered.set(totalOrdered.get() + beerOrderLineDto.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + (beerOrderLineDto.getQuantityAllocated() != null ? beerOrderLineDto.getQuantityAllocated() : 0));
        });

        log.debug("Total Ordered " + totalOrdered.get() + " total Allocated " + totalAllocated);
        return totalOrdered == totalAllocated;
    }

    @Override
    public void deallocateOrder(BeerOrderDto beerOrderDto) {
        beerOrderDto.getBeerOrderLines().forEach(beerOrderLineDto -> {
            BeerInventory inventory = BeerInventory.builder()
                    .beerId(beerOrderLineDto.getBeerId())
                    .upc(beerOrderLineDto.getUpc())
                    .quantityOnHand(beerOrderLineDto.getQuantityAllocated())
                    .build();

            BeerInventory savedInventory = repository.save(inventory);

            log.debug("Saved Inventory for beer upc: " + savedInventory.getUpc() + " inventory id: " + savedInventory.getId());
        });

    }

    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLineDto) {
        List<BeerInventory> beerInventoryList = repository.findAllByUpc(beerOrderLineDto.getUpc());
        beerInventoryList.forEach(beerInventory -> {
            int inventory = (beerInventory.getQuantityOnHand() == null) ? 0 : beerInventory.getQuantityOnHand();
            int orderQty = (beerOrderLineDto.getOrderQuantity() == null) ? 0 : beerOrderLineDto.getOrderQuantity();
            int allocatedQty = (beerOrderLineDto.getQuantityAllocated() == null) ? 0 : beerOrderLineDto.getQuantityAllocated();

            int qtyToAllocate = orderQty - allocatedQty;

            //full allocation
            if (inventory >= qtyToAllocate) {
                inventory = inventory - qtyToAllocate;
                beerOrderLineDto.setQuantityAllocated(orderQty);
                beerInventory.setQuantityOnHand(inventory);

                repository.save(beerInventory);
            } else if (inventory > 0) { //partial allocation
                beerOrderLineDto.setQuantityAllocated(allocatedQty + inventory);
                beerInventory.setQuantityOnHand(0);
                repository.delete(beerInventory);
            }
        });
    }
}
