package com.codegym.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.codegym.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRoleDAO {

    @Autowired
    private EntityManager entityManager;

    public List<String> getRoleNamesById(Long userId) {
        String sql = "Select ur.role.name from " + UserRole.class.getName() + " ur " //
                + " where ur.user.id = :userId ";

        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}