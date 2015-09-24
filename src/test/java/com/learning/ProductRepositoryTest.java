package com.learning;

import com.learning.entity.Inventory;
import com.learning.entity.Product;
import com.learning.repository.ProductRepository;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by amits on 22/09/15.
 */

public class ProductRepositoryTest extends BaseTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFetchingAllProducts() throws Exception {
        List<Product> productList = productRepository.findAll();
        Assert.assertTrue(productList.size() > 0);
    }

    @Test
    public void testAddingNewProduct() throws Exception {
        Product product = new Product();
        product.setProductName("Electric Shaver");
        product.setColor("Black");
        product.setModelNumber("ES34");
        product.setProductionDate(DateTime.now().minusWeeks(12).toDate());
        product.setPurchaseDate(DateTime.now().toDate());
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setTotalItems(340);
        productRepository.save(product);
    }
}
