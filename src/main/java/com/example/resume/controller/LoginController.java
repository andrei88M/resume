package com.example.resume.controller;

import com.example.resume.model.User;
import com.example.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@ModelAttribute("user") User user,
                                   @RequestParam("password2") String password2) {

        if (!user.getPassword().equals(password2)){
            return "/registration";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
