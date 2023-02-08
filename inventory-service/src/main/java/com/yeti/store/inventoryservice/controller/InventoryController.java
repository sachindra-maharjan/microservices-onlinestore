package com.yeti.store.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yeti.store.inventoryservice.dto.InventoryDto;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> isAvailable(List<String> skus) {
        return null;
    }

}
