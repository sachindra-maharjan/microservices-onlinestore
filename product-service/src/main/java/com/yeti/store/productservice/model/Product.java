package com.yeti.store.productservice.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(value = "product")
@Data
@Builder
public class Product {
    @Id
    private Long id;
    private String name;
    private String sku;
    private BigDecimal price;

}
