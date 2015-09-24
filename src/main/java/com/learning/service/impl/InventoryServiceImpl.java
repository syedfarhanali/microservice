package com.learning.service.impl;

import com.learning.dto.InventoryDto;
import com.learning.entity.Inventory;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;
import com.learning.repository.InventoryRepository;
import com.learning.repository.ProductRepository;
import com.learning.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amits on 23/09/15.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<InventoryDto> findAll() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryDto> inventoryDtoList = inventoryList.stream().map(this::inventoryToInventoryDto).collect(Collectors.toList());
        return inventoryDtoList;
    }

    private InventoryDto inventoryToInventoryDto(Inventory inventory) {
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventoryId(inventory.getInventoryId());
        inventoryDto.setTotalItems(inventory.getTotalItems());
        inventoryDto.setProductName(inventory.getProduct().getProductName());
        inventoryDto.setProductId(inventory.getProduct().getProductId());
        return inventoryDto;
    }

    @Transactional
    @Override
    public void addItems(Long productId, int quantity) throws ItemNotFoundException {
        Inventory inventory = inventoryRepository.findByProductProductId(productId);
        if (inventory == null) {
            throw new ItemNotFoundException("No item found for product id " + productId);
        }
        if (inventory != null) {
            inventory.addItems(quantity);
        } else {
            inventory = new Inventory();
            inventory.addItems(quantity);
            inventory.setProduct(productRepository.findOne(productId));
            inventoryRepository.save(inventory);
        }
    }

    @Override
    @Transactional
    public void removeItem(Long productId, int quantity) throws ItemNotFoundException, InsufficientItemStockException {
        Inventory inventory = inventoryRepository.findByProductProductId(productId);
        if (inventory == null) {
            throw new ItemNotFoundException("No item found for product id " + productId);
        }
        inventory.removeItems(quantity);
    }


}
