package com.sicredi.products.service;

import com.sicredi.products.service.model.ProductFilterModel;
import com.sicredi.products.service.model.ProductModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Mono<ProductModel> findOne(String productId);

    Mono<ProductModel> save(ProductModel productModel);

    Mono<ProductModel> save(String productId, ProductModel productModel);

    Flux<ProductModel> find(ProductFilterModel productFilterModelModel);

    Mono<Void> remove(String productId);
}
