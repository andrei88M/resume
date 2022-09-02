package com.example.resume.controller;

import com.example.resume.model.User;
import com.example.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String registrationUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()){
            return "registration";
        }

        if (!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("passwords_equals", "Passwords should be the same");
            return "registration";
        }else if (userService.existsUserByUsername(user.getUsername())){
            model.addAttribute("login", "Come up with another login");
            return "registration";
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
