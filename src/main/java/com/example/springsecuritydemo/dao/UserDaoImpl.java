package com.example.springsecuritydemo.dao;


import com.example.springsecuritydemo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User getUserById(Long id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = (User) entityManager.createQuery("select u from User u where u.id=?1")
                .setParameter(1, id)
                .getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String name) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.name = :name", User.class);
        q.setParameter("name", name);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where u.email = :email", User.class);
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }
}
