package com.learning;

import com.learning.exception.ItemNotFoundException;
import com.learning.service.InventoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by amits on 23/09/15.
 */
public class InventoryServiceTest extends BaseTest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testAddingItemToInventoryForExitingProduct() throws Exception {
        int initialCount = jdbcTemplate.queryForObject("SELECT total_items FROM inventory WHERE product_id =  ?", Integer.class, 1);
        inventoryService.addItems(1L, 30);
        int finalCount = jdbcTemplate.queryForObject("SELECT total_items FROM inventory WHERE product_id =  ?", Integer.class, 1);
        Assert.assertEquals(finalCount, initialCount + 30);
    }


    @Test(expected = ItemNotFoundException.class)
    @Transactional
    public void testAddingItemToInventoryForNonExitingProduct() throws Exception {
        inventoryService.addItems(3467L, 30);

    }
}
