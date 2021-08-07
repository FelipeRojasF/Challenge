package com.la_haus.domain.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
public class Pricing {
    @NotNull(message = "sale price cannot be null")
    private int salePrice;
    private int administrativeFee;
}
