package com.learning;

import com.learning.entity.Inventory;
import com.learning.repository.InventoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amits on 23/09/15.
 */
public class InventoryRepositoryTest extends BaseTest {

    @Autowired
    private InventoryRepository inventoryRepository;

//    @Test
//    @Transactional
//    public void testFetchingInventoryUsingProductName() throws Exception {
//        Inventory inventory = inventoryRepository.findByProductProductName("WiFi-Router");
//        Assert.assertTrue(inventory.getTotalItems() > 0);
//    }



}
