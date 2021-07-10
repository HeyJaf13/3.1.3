package com.example.springsecuritydemo.service;


import com.example.springsecuritydemo.model.Role;

public interface RoleService {
    Role findRoleByString(String s);
    Role findRoleById(Long id);
}
