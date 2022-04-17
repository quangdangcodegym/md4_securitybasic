package com.codegym.validator;

import com.codegym.dao.ProvinceDAO;
import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ProductService;
import com.codegym.service.ProvinceService;
import com.codegym.service.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProvinceValidator implements Validator {

    //@Autowired
    //ProvinceDAO provinceDAO;

    @Autowired
    private ProvinceService provinceService ;

    @Override
    public boolean supports(Class<?> clazz) {
        return Province.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Province province = (Province) target;
        //ValidationUtils.rejectIfEmpty(errors, "firstName", "email.exist");

        System.out.println("province serice");
        System.out.println(provinceService);

        Optional<Province> exist = provinceService.existsByName(province.getName());

        if (!exist.isPresent()) {
            System.out.println("ProvinceValidator " + "Province name: "+ province.getName());
        }


//        if(provinceService.existsByName(province.getName())){
//            System.out.println("ProvinceValidator :"  + province.getName());
//            errors.rejectValue("name", "province.name.exist");
//        }
//        if(provinceService!=null){
//
//        }else{
//            System.out.println("ProvinceValidator " + "provinceService: nulll");
//        }

    }
}
