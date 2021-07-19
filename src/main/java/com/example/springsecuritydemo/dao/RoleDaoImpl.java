package com.example.springsecuritydemo.dao;


import com.example.springsecuritydemo.model.Role;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager manager;

    @Override
    public Role findRoleByString(String s) {
        Optional<Role> role = Optional.of((Role) manager.createQuery("select r from Role r where r.role=?1").setParameter(1, s).getSingleResult());
        return role.get();
    }

    @Override
    public Role findRoleById(Long id) {
        Optional<Role> role = Optional.of((Role) manager.createQuery("select r from Role r where r.id=?1").setParameter(1, id).getSingleResult());
        return role.get();
    }
}
