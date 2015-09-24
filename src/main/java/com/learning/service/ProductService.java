package com.learning.service;

import com.learning.dto.ProductDto;

import java.util.List;

/**
 * Created by amits on 23/09/15.
 */
public interface ProductService {
    List<ProductDto> findAll();
}
