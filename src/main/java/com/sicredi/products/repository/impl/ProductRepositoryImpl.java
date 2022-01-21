package com.sicredi.products.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.sicredi.products.repository.ProductDAO;
import com.sicredi.products.repository.ProductRepository;
import com.sicredi.products.repository.entity.ProductEntity;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductDAO productDAO;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public ProductRepositoryImpl(ProductDAO productDAO, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.productDAO = productDAO;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<ProductEntity> save(ProductEntity productEntity) {
        return productDAO.save(productEntity);
    }

    @Override
    public Mono<ProductEntity> findOne(Query query) {
        return reactiveMongoTemplate.findOne(query, ProductEntity.class);
    }

    @Override
    public Mono<ProductEntity> findOne(String productId) {
        var query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));
        return findOne(query);
    }

    @Override
    public Flux<ProductEntity> find(Query query) {
        return reactiveMongoTemplate.find(query, ProductEntity.class);
    }

    @Override
    public Mono<DeleteResult> remove(String productId) {
        var query = new Query();
        query.addCriteria(Criteria.where("productId").is(productId));
        return reactiveMongoTemplate.remove(query, ProductEntity.class);
    }

}
