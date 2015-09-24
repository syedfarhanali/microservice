package com.learning.controller;

import com.learning.dto.ProductDto;
import com.learning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by amits on 23/09/15.
 */
@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll")
    List<ProductDto> getAll() {
        return productService.findAll();
    }
}
