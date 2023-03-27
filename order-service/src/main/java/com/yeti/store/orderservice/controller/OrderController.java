package com.yeti.store.orderservice.controller;

import java.util.concurrent.CompletableFuture;

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
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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
  @TimeLimiter(name = "inventory")
  @RateLimiter(name = "inventory")
  public CompletableFuture<String> health() {
      return CompletableFuture.supplyAsync(() -> orderService.resilience4JTestOnly());
  }

  public CompletableFuture<String> fallbackMethod(RuntimeException exception) {
    return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong. Please try again later.");
  }
  
}
