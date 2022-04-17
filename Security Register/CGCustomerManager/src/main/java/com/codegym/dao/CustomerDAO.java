package com.codegym.dao;

import com.codegym.model.Customer;

public interface CustomerDAO {
    Customer findCustomerByEmail(String email);
}
