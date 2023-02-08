package com.yeti.store.inventoryservice.service;

import java.util.List;

import com.yeti.store.inventoryservice.dto.InventoryDto;
import com.yeti.store.inventoryservice.exception.ServiceException;

public interface InventoryService {
    
    List<InventoryDto> isInStock(List<String> skus) throws ServiceException;

}
