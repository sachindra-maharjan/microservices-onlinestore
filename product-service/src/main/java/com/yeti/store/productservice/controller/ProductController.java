package com.yeti.store.productservice.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yeti.store.productservice.dto.ProductDto;
import com.yeti.store.productservice.exception.ServiceException;
import com.yeti.store.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(ProductDto productDto) {
        Optional<ProductDto> productOptional = Optional.of(productDto);
        productOptional.orElseThrow(() -> new ServiceException("Product information is required."));
        productService.create(productDto);
    }

}
