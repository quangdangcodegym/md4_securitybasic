package com.codegym.service;

import java.util.List;

public interface ProductService<T> {
    List<T> findAllProducts();
    void saveProduct(T t);
    void removeProduct(Long id);
    T findProductById(Long id);
}
