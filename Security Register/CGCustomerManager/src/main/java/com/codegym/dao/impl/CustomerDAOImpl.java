package com.codegym.dao.impl;

import com.codegym.dao.CustomerDAO;
import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Customer findCustomerByEmail(String email) {
        String sql = "select c from " + Customer.class.getName() + " c "
                + " where c.email like :email";

        System.out.println("CustomerDAOImpl " + "findCustomerByEmail " + sql);

        try{
            TypedQuery<Customer> query = this.entityManager.createQuery(sql, Customer.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }


    }
}
