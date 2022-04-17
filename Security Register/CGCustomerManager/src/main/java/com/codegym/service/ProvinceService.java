package com.codegym.service;

import com.codegym.model.Province;

import java.util.Optional;

public interface ProvinceService {
    Iterable<Province> findAll();

    Province findById(Long id);

    void save(Province province);

    void remove(Long id);

    boolean existsById(Long id);

    Optional<Province> existsByName(String name);
}
