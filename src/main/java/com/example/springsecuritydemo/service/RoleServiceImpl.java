package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dao.RoleDao;
import com.example.springsecuritydemo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(@Autowired RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role getRoleByString(String s) {
        return roleDao.findRoleByString(s);
    }

}
