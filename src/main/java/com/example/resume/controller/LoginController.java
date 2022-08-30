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
    public String registrationUser(@ModelAttribute("user") User user) {

        if (!user.getPassword().equals(user.getPassword2())){
            return "redirect:/registration";
        }else if (userService.existsUserByUsername(user.getUsername())){
            return "redirect:/registration";
        }
        userService.save(user);
        user.setPassword("");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
