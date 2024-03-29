package com.yeti.store.orderservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.yeti.store.orderservice.dto.InventoryDto;
import com.yeti.store.orderservice.dto.OrderDto;
import com.yeti.store.orderservice.dto.OrderLineItemDto;
import com.yeti.store.orderservice.exception.ServiceException;
import com.yeti.store.orderservice.model.Order;
import com.yeti.store.orderservice.model.Event;
import com.yeti.store.orderservice.model.OrderLineItem;
import com.yeti.store.orderservice.repository.OrderRepository;
import com.yeti.store.orderservice.service.OrderService;

// import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
// @Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public String createOrder(OrderDto orderDto) throws ServiceException {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItems = orderDto.getOrderLineItems().stream()
            .map(this::mapToDto)
            .toList();
        
        order.setOrderLineItems(orderLineItems);

        List<String> skus = order.getOrderLineItems().stream()
            .map(OrderLineItem::getSkuCode)
            .toList();
        
        InventoryDto[] inventoryDtos = webClientBuilder.build().get()
            .uri("http://inventory-service/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skus). build())
            .retrieve()
            .bodyToMono(InventoryDto[].class)
            .block();    
        
        boolean allProductsInStock = Arrays.stream(inventoryDtos)
            .allMatch(InventoryDto::isInStock);
        if(allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new Event(UUID.randomUUID().node(), "Order placed."));
        } else {
            throw new ServiceException("Product is not in stock. Please try again later.");
        }
        
        return "Order placed successfully";
    }
    
    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .skuCode(orderLineItemDto.getSkuCode())
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .build();
    }

    @Override
    public String  resilience4JTestOnly() {
        try{
            kafkaTemplate.send("notificationTopic", new Event( new Random().nextLong(), "Health check."));
        } catch(RuntimeException ex) {
            log.error("Unable to send message to kafka notificationTopic", ex);
        }
        
        String result = webClientBuilder.build().get()
            .uri("http://inventory-service/inventory/health")
            .retrieve()
            .bodyToMono(String.class)
            .block();
        return result;
    }

}
