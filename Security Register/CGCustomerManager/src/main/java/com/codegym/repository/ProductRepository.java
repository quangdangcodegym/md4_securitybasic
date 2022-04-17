package com.codegym.repository;

import com.codegym.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
