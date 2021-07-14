package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
    User getUserByName(String name);
    User getUserByEmail(String Email);
}
