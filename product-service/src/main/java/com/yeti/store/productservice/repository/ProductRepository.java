package com.yeti.store.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yeti.store.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long>{
    
}
