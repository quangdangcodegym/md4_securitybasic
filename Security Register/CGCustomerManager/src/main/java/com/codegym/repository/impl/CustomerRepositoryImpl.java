package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerRepositoryImpl implements CustomerRepository<Customer> {
    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Customer> findAllCustomer() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findCustomerById(Long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer c where  c.id=:id", Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void saveCustomer(Customer customer) {
        if (customer.getId() != null) {
            em.merge(customer);
        } else {
            em.persist(customer);
        }
    }

    @Override
    public void removeCustomer(Customer customer) {
        Customer cus = findCustomerById(customer.getId());
        if (cus != null) {
            em.remove(cus);
        }
    }
}
