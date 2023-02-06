package com.yeti.store.productservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yeti.store.productservice.dto.ProductDto;
import com.yeti.store.productservice.model.Product;
import com.yeti.store.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    
    public void create(ProductDto productDto) {
        Product product = Product.builder()
            .name(productDto.getName())
            .sku(productDto.getSku())
            .price(productDto.getPrice())
            .build();     
        Product newProduct = productRepository.insert(product); 
        log.info("New product {} added.", newProduct.getId());
    }

}
