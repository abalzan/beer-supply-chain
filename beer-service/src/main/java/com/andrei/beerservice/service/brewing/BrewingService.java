package com.andrei.beerservice.service.brewing;

import com.andrei.beerservice.config.JmsConfig;
import com.andrei.beerservice.domain.Beer;
import com.andrei.beerservice.repository.BeerRepository;
import com.andrei.beerservice.service.inventory.BeerInventoryService;
import com.andrei.beerservice.web.mapper.BeerMapper;
import com.andrei.common.events.BrewBeerEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper mapper;

    @Scheduled(fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();
        beers.forEach(beer -> {
            Integer invQuantityOnHand = beerInventoryService.getOnHandInventory(beer.getId());
            log.debug("Min on hand " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQuantityOnHand);
            if (beer.getMinOnHand() >= invQuantityOnHand) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(mapper.beerToBeerDto(beer)));
            }
        });
    }
}
