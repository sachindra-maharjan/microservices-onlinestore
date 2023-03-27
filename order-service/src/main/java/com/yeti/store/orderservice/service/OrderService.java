package com.yeti.store.orderservice.service;

import java.util.concurrent.CompletableFuture;

import com.yeti.store.orderservice.dto.OrderDto;
import com.yeti.store.orderservice.exception.ServiceException;

public interface OrderService {

    String createOrder(OrderDto orderDto) throws ServiceException;

    String resilience4JTestOnly();

}