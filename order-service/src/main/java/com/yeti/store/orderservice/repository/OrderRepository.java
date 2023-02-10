package com.yeti.store.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeti.store.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
