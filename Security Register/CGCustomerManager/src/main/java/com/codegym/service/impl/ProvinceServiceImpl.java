package com.codegym.service.impl;

import com.codegym.model.Province;
import com.codegym.repository.ProvinceRepository;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        Optional<Province> op = provinceRepository.findById(id);
        return op.get();
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return provinceRepository.existsById(id);
    }

    @Override
    public Optional<Province> existsByName(String name) {
        return provinceRepository.existsByName(name);
    }


}
