package com.learning.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by amits on 24/09/15.
 */
@Entity
@Setter
@Getter
public class Category extends BaseEntity {

    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
