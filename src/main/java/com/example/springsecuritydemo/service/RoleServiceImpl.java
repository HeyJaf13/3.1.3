package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.dao.RoleDao;
import com.example.springsecuritydemo.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }


    @Override
    public Role findRoleByString(String s) {
        return roleDao.findRoleByString(s);
    }

}
