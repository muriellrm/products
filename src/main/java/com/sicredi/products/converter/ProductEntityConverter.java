package com.sicredi.products.converter;

import com.sicredi.products.repository.entity.ProductEntity;
import com.sicredi.products.service.model.ProductModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter {

    private final ModelMapper modelMapper;

    public ProductEntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductEntity applyValues(ProductModel productModel) {
        return modelMapper.map(productModel,ProductEntity.class);
    }

    public ProductEntity applyValues(ProductEntity productEntity,ProductModel productModel) {
        modelMapper.map(productModel,productEntity);
        return productEntity.toBuilder().build();
    }

}
