package com.yeti.store.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yeti.store.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, Long>{
    
}
