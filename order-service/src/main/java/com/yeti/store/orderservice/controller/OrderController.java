package com.yeti.store.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yeti.store.orderservice.dto.OrderDto;
import com.yeti.store.orderservice.exception.ServiceException;
import com.yeti.store.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String placeOrder(OrderDto orderDto) throws ServiceException{
    return orderService.createOrder(orderDto);
  }

  @GetMapping("/health")
  @ResponseStatus(HttpStatus.OK)
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
  public String health() {
      return orderService.resilience4JTestOnly();
  }

  public String fallbackMethod(RuntimeException exception) {
    return "Oops! Something went wrong. Please try again later.";
  }
  
}
