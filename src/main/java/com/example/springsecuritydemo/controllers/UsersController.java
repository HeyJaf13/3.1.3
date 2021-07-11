package com.example.springsecuritydemo.controllers;


import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.service.RoleService;
import com.example.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@Controller
@RequestMapping("/admin")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("admin", userService.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("User") User user, @RequestParam(value = "AdminId", defaultValue = "2") String[] userRoleId) {
        setRolesFromStringArr(user, userRoleId);
        userService.saveUser(user);
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

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
