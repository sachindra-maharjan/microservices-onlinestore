package com.yeti.store.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDto {
    String skuCode;
    boolean inStock;
}
