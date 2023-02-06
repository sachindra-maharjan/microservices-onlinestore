package com.yeti.store.productservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
    
    private String name;
    private String sku;
    private BigDecimal price;

}
