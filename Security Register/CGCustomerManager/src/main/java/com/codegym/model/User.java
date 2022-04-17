package com.codegym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "user_uk", columnNames = "name") })
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 36, nullable = false)
    private String name;

    @Column(name = "password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

    public User(){}

    public User(Long id, String name, String encrytedPassword, boolean enabled) {
        this.id = id;
        this.name = name;
        this.encrytedPassword = encrytedPassword;
        this.enabled = enabled;
    }

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

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}