package com.example.springsecuritydemo.controllers;

import com.example.springsecuritydemo.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "")
    public String mainPage(ModelMap model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeUser", currentUser);
        return "adminPage";
    }
}
