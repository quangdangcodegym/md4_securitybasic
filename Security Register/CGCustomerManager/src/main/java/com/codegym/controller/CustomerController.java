package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.CategoryService;
import com.codegym.service.CustomerService;
import com.codegym.service.ProvinceService;
import com.codegym.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@ControllerAdvice
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    private MessageSource messageSource;


    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @ModelAttribute
    public void commonModelAttributes(Model model){
        model.addAttribute("categories", categoryService.findAllCategories());
    }

    @Autowired
    private EmailValidator emailValidator;


    @GetMapping("/customer/create")
    public ModelAndView showCreateForm(){
        System.out.println("CustomerController " + "showCreateForm ");
        ModelAndView m = new ModelAndView("/customer/add");

        m.addObject("customer", new Customer());
        //m.addObject("message", "Create customer");
        return  m;
    }

    @PostMapping("/customer/create")
    public ModelAndView saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult){
        System.out.println("CustomerController " + "saveCustomer ");

        ModelAndView m;
        //new EmailValidator().validate(customer, bindingResult);
        //new LastNameValidator().validate(customer, bindingResult);

        System.out.println(customer.getFirstName() +"---" + customer.getLastName());
        if(!bindingResult.hasErrors()){
            System.out.println("CustomerController " + "saveCustomer " + "cus: " + customer.toString());
            if(customerService.findCustomerByEmail(customer.getEmail())==null){
                customerService.saveCustomer(customer);
                m = new ModelAndView("redirect:/customer/list");
            }else{
                System.out.println("CustomerController " + "saveCustomer " + "cus: " + "is exist");
                m = new ModelAndView("/customer/add");
                String mes = messageSource.getMessage("email.exist", null, Locale.getDefault());
                m.addObject("error", mes);
                m.addObject("customer", customer);
            }
        }else{
            m = new ModelAndView("/customer/add");
        }
        return  m;
    }
    @GetMapping("/customer/list")
    public ModelAndView listCustomer(){
        System.out.println("CustomerController " + "listCustomer ");
        ModelAndView m = new ModelAndView("/customer/list");
        //List<Customer> listCustomers = customerService.findAllCustomer();

        System.out.println(customerService.findAllCustomer());
        System.out.println(customerService.findAllCustomer());
        m.addObject("customers", customerService.findAllCustomer());
        return m;
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Customer cus = (Customer) customerService.findCustomerById(id);
        if(cus!=null){
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", cus);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("redirect:/customer/list");
            return modelAndView;
        }
    }
    @PostMapping("/customer/edit")
    public ModelAndView editCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult){
        System.out.println("CustomerController " + "editCustomer ");

        ModelAndView m;
        System.out.println(customer.getFirstName() +"---" + customer.getLastName());
        String url = "/customer/edit/" + customer.getId();
        if(!bindingResult.hasErrors()){
            System.out.println("CustomerController " + "editCustomer " + "cus: " + customer.toString());
            if(customerService.findCustomerByEmail(customer.getEmail())==null){
                customerService.saveCustomer(customer);
                m = new ModelAndView("redirect:/customer/list");
            }else{
                System.out.println("CustomerController " + "editCustomer " + "cus: " + "is exist");
                m = new ModelAndView("/customer/edit");
                String mes = messageSource.getMessage("email.exist", null, Locale.getDefault());
                m.addObject("error", mes);
                m.addObject("customer", customer);
            }
        }else{
            m = new ModelAndView("/customer/edit");
        }
        return  m;
    }

    @GetMapping("/customer/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Customer customer = (Customer) customerService.findCustomerById(id);
        if(customer != null) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("redirect:/customer/list");
            return modelAndView;
        }
    }

    @PostMapping("/customer/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer){
        customerService.removeCustomer(customer.getId());
        return "redirect:/customer/list";
    }
}
