package com.sicredi.products.repository;

import com.sicredi.products.repository.entity.ProductEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductDAO extends ReactiveMongoRepository<ProductEntity, String> {
}
