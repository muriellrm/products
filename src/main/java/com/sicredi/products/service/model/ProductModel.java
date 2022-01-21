package com.sicredi.products.service.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductModel {

    private String productId;

    private String name;

    private Double price;

}



