package com.codegym.utils;

import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceUtils {
    @Autowired
    ProductService productService;
    public static boolean checkProvinceExist(String nameProvince){

        if(nameProvince.length()>5){
            return true;
        }
        return false;
    }
}
