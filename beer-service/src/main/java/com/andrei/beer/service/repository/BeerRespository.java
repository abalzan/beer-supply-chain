package com.andrei.beer.service.repository;

import com.andrei.beer.service.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRespository extends JpaRepository<Beer, UUID> {
}
