package com.learning.repository;

import com.learning.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by amits on 26/09/15.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);
}
