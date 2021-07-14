package com.example.springsecuritydemo.controllers;

import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.RoleService;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(@Autowired UserService userService, @Autowired RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "")
    public String allUsers(ModelMap model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeUser", currentUser);
        model.addAttribute("users", userService.getAllUsers());
        return "adminPage";
    }

    @RequestMapping(value = "/remove")
    public String removeUser(@ModelAttribute("Email") String email) {
        Long id = userService.getUserByEmail(email).getId();
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("User") User user, @RequestParam(value = "AdminId") String[] userRoleId) {
        setRolesFromStringArr(user, userRoleId);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @RequestMapping("/update")
    public String updateUser(@ModelAttribute("User") User user, @ModelAttribute("roles") String[] roles){
        setRolesFromStringArr(user, roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    private void setRolesFromStringArr(User user, String[] roles) {
        String ADMIN_ROLE_ID = "1";
        Set<String> userRoles = Set.of(roles);
        if (userRoles.contains(ADMIN_ROLE_ID)) {
            user.setRoles(Set.of(roleService.findRoleByString("ROLE_ADMIN"), roleService.findRoleByString("ROLE_USER")));
        } else {
            user.setRoles(Set.of(roleService.findRoleByString("ROLE_USER")));
        }
    }
}
