package com.example.springsecuritydemo.dao;

import com.example.springsecuritydemo.model.Role;
import org.springframework.stereotype.Component;


@Component
public interface RoleDao {
    Role findRoleByString(String s);
    Role findRoleById(Long id);
}


