package com.codegym.repository;

import com.codegym.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUserByNameContaining(String name);
}
