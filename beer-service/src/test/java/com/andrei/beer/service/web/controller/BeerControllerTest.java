package com.andrei.beer.service.web.controller;

import com.andrei.beer.service.web.model.BeerDto;
import com.andrei.beer.service.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void getBeerById() throws Exception {
    mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception{
        BeerDto beerDto = createBeerDto();
        String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = createBeerDto();
        String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

    }

    BeerDto createBeerDto() {
        return BeerDto.builder()
                .beerName("Fake name")
                .styleEnum(BeerStyleEnum.ALE)
                .price(new BigDecimal("1.99"))
                .upc(1234124L)
                .build();
    }
}