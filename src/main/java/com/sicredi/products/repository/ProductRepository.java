package com.sicredi.products.repository;

import com.mongodb.client.result.DeleteResult;
import com.sicredi.products.repository.entity.ProductEntity;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<ProductEntity> save(ProductEntity productEntity);

    Mono<ProductEntity> findOne(Query query);

    Mono<ProductEntity> findOne(String productId);

    Flux<ProductEntity> find(Query query);

    Mono<DeleteResult> remove(String productId);
}
