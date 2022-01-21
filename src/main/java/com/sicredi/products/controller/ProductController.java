package com.sicredi.products.controller;

import com.sicredi.products.service.ProductService;
import com.sicredi.products.service.model.ProductFilterModel;
import com.sicredi.products.service.model.ProductModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping()
    public Mono<ProductModel> create(@RequestBody ProductModel productModel) {
        return productService.save(productModel);
    }

    @PutMapping("{productId}")
    public Mono<ProductModel> put(@PathVariable String productId, @RequestBody ProductModel productModel) {
        return productService.save(productId, productModel);
    }

    @GetMapping("{productId}")
    public Mono<ProductModel> get(@PathVariable String productId) {
        return productService.findOne(productId);
    }

    @GetMapping()
    public Flux<ProductModel> getAll(
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) String name
    ) {
        return productService.find(ProductFilterModel.builder()
                .priceMax(priceMax)
                .priceMin(priceMin)
                .name(name)
                .build());
    }

    @DeleteMapping("{productId}")
    public Mono<Void> delete(@PathVariable String productId) {
        return productService.remove(productId);
    }
}
