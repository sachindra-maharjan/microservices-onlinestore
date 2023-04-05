package com.yeti.store.productservice.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.yeti.store.productservice.dto.ProductRequest;
import com.yeti.store.productservice.dto.ProductResponse;
import com.yeti.store.productservice.model.Product;
import com.yeti.store.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    
    public void create(ProductRequest productDto) {
        Product product = Product.builder()
            .name(productDto.getName())
            .sku(productDto.getSku())
            .price(productDto.getPrice())
            .build();     
        Product newProduct = productRepository.insert(product); 
        log.info("New product {} added.", newProduct.getId());
    }

    public List<ProductResponse> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll().stream()
                    .map(this::mapProductToProductResponse)
                    .toList();
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .build();
    }

}
