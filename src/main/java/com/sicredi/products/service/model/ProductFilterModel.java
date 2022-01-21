package com.sicredi.products.service.model;

import lombok.*;

@Getter
@Builder(toBuilder = true)
public class ProductFilterModel {

    private Double priceMin;

    private Double priceMax;

    private String name;
}
