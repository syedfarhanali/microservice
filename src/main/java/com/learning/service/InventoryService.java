package com.learning.service;

import com.learning.dto.InventoryDto;
import com.learning.exception.InsufficientItemStockException;
import com.learning.exception.ItemNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by amits on 23/09/15.
 */
public interface InventoryService {

    List<InventoryDto> findAll();

    @Transactional
    void addItems(Long productId, int quantity) throws ItemNotFoundException;

    @Transactional
    void removeItem(Long productId, int quantity) throws ItemNotFoundException, InsufficientItemStockException;
}
