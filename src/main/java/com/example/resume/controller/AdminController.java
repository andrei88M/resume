package com.example.resume.controller;

import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.service.ProfileService;
import com.example.resume.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    private final ProfileService profileService;

    @GetMapping("/admin")
    public String admin(Model model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/{user_id}/profiles")
    public String profiles(@PathVariable("user_id") Long user_id, Model model) {
        List<Profile> profiles = profileService.findAllByUser_id(user_id);
        model.addAttribute("profiles", profiles);
        return "admin/user_id/profiles";
    }

    @DeleteMapping("/admin/{user_id}/profiles/{profile_id}")
    public String deleteProfile(@PathVariable("profile_id") Long profile_id, @PathVariable("user_id") String user_id) {
        profileService.deleteById(profile_id);
        return "redirect:/admin/" + user_id + "/profiles";
    }

    @DeleteMapping("/admin/{user_id}")
    public String deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteById(user_id);
        return "redirect:/admin";
    }
}
