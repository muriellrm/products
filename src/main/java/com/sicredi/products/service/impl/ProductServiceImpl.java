package com.sicredi.products.service.impl;

import com.sicredi.products.converter.ProductEntityConverter;
import com.sicredi.products.converter.ProductModelConverter;
import com.sicredi.products.repository.ProductRepository;
import com.sicredi.products.service.ProductService;
import com.sicredi.products.service.model.ProductFilterModel;
import com.sicredi.products.service.model.ProductModel;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityConverter productEntityConverter;
    private final ProductModelConverter productModelConverter;

    public ProductServiceImpl(ProductRepository productRepository, ProductEntityConverter productEntityConverter, ProductModelConverter productModelConverter) {
        this.productRepository = productRepository;
        this.productEntityConverter = productEntityConverter;
        this.productModelConverter = productModelConverter;
    }

    @Override
    public Mono<ProductModel> findOne(String productId){
        return productRepository.findOne(productId).map(productModelConverter::mapFrom);
    }


    @Override
    public Mono<ProductModel> save(ProductModel productModel){
        return Mono.just(productModel)
                .map(productEntityConverter::applyValues)
                .flatMap(productRepository::save)
                .map(productModelConverter::mapFrom);
    }

    @Override
    public Mono<ProductModel> save(String productId, ProductModel productModel) {
        return productRepository.findOne(productId)
                .zipWith(Mono.just(productModel), productEntityConverter::applyValues)
                .flatMap(productRepository::save)
                .map(productModelConverter::mapFrom);
    }

    @Override
    public Flux<ProductModel> find(ProductFilterModel productFilterModelModel) {
        var query = new Query();
        var criteria = new ArrayList<Criteria>();

        if(productFilterModelModel.getPriceMin() != null) {
            criteria.add(Criteria.where("price").gte(productFilterModelModel.getPriceMin()));
        }
        if(productFilterModelModel.getPriceMax() != null) {
            criteria.add(Criteria.where("price").lte(productFilterModelModel.getPriceMax()));
        }
        if(productFilterModelModel.getName() != null) {
            criteria.add(Criteria.where("name").regex(productFilterModelModel.getName(), "i"));
        }

        if(!CollectionUtils.isEmpty(criteria)) {
            query.addCriteria(new Criteria()
                    .andOperator(
                            criteria.toArray(Criteria[]::new)
                    ));
        }

        return productRepository.find(query).map(productModelConverter::mapFrom);
    }

    @Override
    public Mono<Void> remove(String productId){
        return productRepository.remove(productId).then();
    }

}
