package com.andrei.msscbeerservice.bootstrap;

import com.andrei.msscbeerservice.domain.Beer;
import com.andrei.msscbeerservice.repository.BeerRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRespository beerRespository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRespository.count()==0){
            beerRespository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(981263816L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRespository.save(Beer.builder()
                    .beerName("Galaxy Car")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(581263816L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
