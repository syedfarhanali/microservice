package com.learning.service.impl;

import com.learning.dto.ProductDto;
import com.learning.entity.Product;
import com.learning.repository.ProductRepository;
import com.learning.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by amits on 23/09/15.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;



    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = productList.stream().map(this::productToProductDto).collect(Collectors.toList());
        return productDtoList;
    }

    private ProductDto productToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getId());
        productDto.setProductName(product.getName());
        return productDto;
    }
}
