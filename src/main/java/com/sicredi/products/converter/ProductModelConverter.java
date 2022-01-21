package com.sicredi.products.converter;

import com.sicredi.products.repository.entity.ProductEntity;
import com.sicredi.products.service.model.ProductModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductModelConverter {

    private final ModelMapper modelMapper;

    public ProductModelConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductModel mapFrom(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductModel.class);
    }
}
