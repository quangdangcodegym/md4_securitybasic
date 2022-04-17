package com.codegym.controller;


import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import com.codegym.validator.EmailValidator;
import com.codegym.validator.ProvinceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ProvinceController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Province> provinces = provinceService.findAll();
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView showCreateForm() {
        System.out.println(provinceService);
        System.out.println("ProvinceController: " + "showCreateForm");
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());

        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@Valid @ModelAttribute("province") Province province, BindingResult bindingResult) {
        //new ProvinceValidator().validate(province, bindingResult);
        ModelAndView modelAndView;

        if (provinceService.existsByName(province.getName())!=null) {
            //bindingResult.rejectValue("name", "Name is exist");
            System.out.println("ProvinceController " + "saveProvince " + province.getName() +  " is exist");
            modelAndView  = new ModelAndView("/province/create");

            String provinceExist = messageSource.getMessage("province.name.exist", null, Locale.getDefault());
            modelAndView.addObject("error", provinceExist);
            return  modelAndView;
        }

        if (!bindingResult.hasErrors()) {
            modelAndView  = new ModelAndView("redirect:/provinces");
            System.out.println("ProvinceController "+ "saveProvince " + province.getName());

            provinceService.save(province);
            modelAndView.addObject("province", new Province());
            return  modelAndView;
        } else {
            modelAndView = new ModelAndView("/province/create");
            modelAndView.addObject("province", province);
            return  modelAndView;
        }
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        System.out.println("ProvinceController " + " showEditForm " + id);
        Province province = provinceService.findById(id);
        if (province != null) {
            ModelAndView modelandview = new ModelAndView("/province/edit");
            modelandview.addObject("province", province);
            return  modelandview;
        } else {
            ModelAndView modelAndView = new ModelAndView("/province/list");
            return modelAndView;
        }
    }

    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@Valid @ModelAttribute("province") Province province, Model model, BindingResult bindingResult){
        System.out.println("ProvinceController " + " updateProvince " + province.getId());


        if(!bindingResult.hasErrors()){
            provinceService.save(province);
            ModelAndView modelandview = new ModelAndView("redirect:/provinces");
            return modelandview;
        }else{
            ModelAndView modelandview = new ModelAndView("/province/edit");
            modelandview.addObject("province", province);
            return modelandview;
        }
    }
    @GetMapping("/delete-province/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        System.out.println("ProvinceController " + " showDeleteForm " + id);

        Province province = provinceService.findById(id);
        if(province!=null){
            ModelAndView m = new ModelAndView("/province/delete");
            m.addObject("province", province);
            return  m;
        }else{
//            ModelAndView modelAndView = new ModelAndView("/error.404");
//            return modelAndView;
            ModelAndView modelandview = new ModelAndView("redirect:/provinces");
            return modelandview;
        }
    }

    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("province") Province province){
        System.out.println("ProvinceController " + " deleteProvince " + province.getId());

        provinceService.remove(province.getId());
        return "redirect:provinces";
    }

}
