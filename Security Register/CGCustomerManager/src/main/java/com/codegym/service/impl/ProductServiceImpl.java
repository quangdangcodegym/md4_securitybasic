package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService<Product> {
    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(Long id) {

    }

    @Override
    public Product findProductById(Long id) {
        Optional<Product> p = productRepository.findById(id);
        return p.get();
    }
}
