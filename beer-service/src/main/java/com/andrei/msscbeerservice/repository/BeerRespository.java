package com.andrei.msscbeerservice.repository;

import com.andrei.msscbeerservice.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRespository extends JpaRepository<Beer, UUID> {
}
