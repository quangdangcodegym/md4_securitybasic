package com.codegym.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="province")
public class Province implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=5, max=100, message = "{province.name.minErr}")
    @NotNull(message = "{province.name.notNull}")
    private String name;

    public Province(){}

    public Province(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "province")
    private Set<Customer> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
