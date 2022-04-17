package com.codegym.service.impl;

import com.codegym.dao.UserRoleDAO;
import com.codegym.model.User;
import com.codegym.repository.UserRepository;
import com.codegym.service.RoleService;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userService.findUserByNameContaining(userName).get(0);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user.getName() + " -- " + user.getId());

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.userRoleDAO.getRoleNamesById(user.getId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        String str = "";
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                str += role;
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        System.out.println("UserDetailsServiceImpl: roleNames.size(): "+ roleNames.size() +" : "+ user.getName() + " " + str);

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User (user.getName(), //
                user.getEncrytedPassword(), grantList);

        return userDetails;
    }

}
