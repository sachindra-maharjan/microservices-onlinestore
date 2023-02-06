package com.yeti.store.productservice.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    
    private String name;
    private String sku;
    private BigDecimal price;

}
