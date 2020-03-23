package com.andrei.brewery.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

    static final long serialVersionUID = -1294217468970048289L;

    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    private OffsetDateTime createDate;

    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotNull
    private String styleEnum;

    @NotNull
    private String upc;

    @NotNull
    @Positive
    private BigDecimal price;

    private Integer quantityOnHand;

}
