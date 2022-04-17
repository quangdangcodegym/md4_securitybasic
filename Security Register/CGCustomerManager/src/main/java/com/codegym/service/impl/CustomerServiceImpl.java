package com.codegym.service.impl;

import com.codegym.dao.CustomerDAO;
import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import com.codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService<Customer> {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDAO customerDAO;


    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAllCustomer();
    }

    @Override
    public Customer findCustomerById(Long id) {

        return (Customer) customerRepository.findCustomerById(id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Override
    public void removeCustomer(Long id) {
        customerRepository.removeCustomer(id);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDAO.findCustomerByEmail(email);
    }


}
