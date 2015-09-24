package com.learning.controller;

import com.learning.dto.InventoryDto;
import com.learning.repository.InventoryRepository;
import com.learning.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by amits on 23/09/15.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/findAll")
   public List<InventoryDto> findAll() {
       return inventoryService.findAll();
   }

}
