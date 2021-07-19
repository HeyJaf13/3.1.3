package com.example.springsecuritydemo.controllers;


import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.RoleService;
import com.example.springsecuritydemo.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(@Autowired UserService userService, @Autowired RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping (value = "/getActiveUser")
    public ResponseEntity<User> getActiveUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping (value = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(Long.valueOf(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping (value = "/user")
    public void addUser(@RequestBody String json){
        try {
            User user =  new ObjectMapper().readValue(json, User.class);
            setRolesByBoolean(user, json.endsWith("\"Admin\"}"));
            userService.saveUser(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PutMapping (value = "/user/{id}")
    public void editUser(@PathVariable String id, @RequestBody String json) {
        try {
            User user =  new ObjectMapper().readValue(json, User.class);
            user.setId(Long.valueOf(id));
            setRolesByBoolean(user, json.endsWith("\"Admin\"}"));
            userService.updateUser(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping (value = "/user/{id}")
    public void removeUser(@PathVariable String id) {
        userService.deleteUserById(Long.valueOf(id));
    }

    private void setRolesByBoolean(User user, boolean isAdmin) {
        if (isAdmin) {
            user.setRoles(Set.of(roleService.getRoleByString("ROLE_ADMIN"), roleService.getRoleByString("ROLE_USER")));
        } else {
            user.setRoles(Set.of(roleService.getRoleByString("ROLE_USER")));
        }
    }
}
