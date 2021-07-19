package com.example.springsecuritydemo.controllers;


import com.example.springsecuritydemo.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "")
    public String getStandardUser(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("activeUser", user);
        return "userPage";
    }
}
