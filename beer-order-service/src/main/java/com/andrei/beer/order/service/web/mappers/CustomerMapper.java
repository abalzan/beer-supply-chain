package com.andrei.beer.order.service.web.mappers;

import com.andrei.beer.order.service.domain.Customer;
import com.andrei.brewery.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(CustomerDto dto);
}
