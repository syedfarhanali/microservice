package com.learning.repository;

import com.learning.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by amits on 22/09/15.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
