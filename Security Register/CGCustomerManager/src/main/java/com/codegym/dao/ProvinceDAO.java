package com.codegym.dao;

import com.codegym.model.Province;
import com.codegym.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.pattern.PathPatternRouteMatcher;

import javax.persistence.*;
import java.util.List;

@Service
@Transactional
public class ProvinceDAO {
    @Autowired
    private EntityManager entityManager;

    public Province getProvinceByName(String name) {

        String sql = "select p from " + Province.class.getName() + " p "
                + " where p.name like %:name%";

        System.out.println("ProvinceDAO " + "query " + sql);
        if(entityManager!=null){
            TypedQuery<Province> query = this.entityManager.createQuery(sql, Province.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        }else{
            return null;
        }

    }
}
