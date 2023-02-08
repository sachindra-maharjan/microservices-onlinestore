package com.yeti.store.inventoryservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yeti.store.inventoryservice.dto.InventoryDto;
import com.yeti.store.inventoryservice.exception.ServiceException;
import com.yeti.store.inventoryservice.service.InventoryService;
import com.yeti.store.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    public List<InventoryDto> isInStock(List<String> skus) throws ServiceException {
        log.info("Checking skus in inventory");
        return inventoryRepository.findBySkuCodeIn(skus).stream()
            .map(inventory -> InventoryDto.builder()
                            .skuCode(inventory.getSkuCode())
                            .inStock(inventory.getQuantity() > 0)
                            .build())
            .toList();
    }
    
}
