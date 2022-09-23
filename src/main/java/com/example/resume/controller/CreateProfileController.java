package com.example.resume.controller;

import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.service.ProfileService;
import com.example.resume.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@PreAuthorize("hasAuthority('USER')")
@Slf4j
@RequiredArgsConstructor
public class CreateProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @GetMapping("/profiles/create-profile")
    public String create(@ModelAttribute("profile") Profile profile) {
        return "profiles/create_profile";
    }

    @PostMapping("/profiles/create-profile")
    public String createProfile(@ModelAttribute("profile") @Valid Profile profile,
                                BindingResult bindingResult,
                                Principal principal) {
        if (bindingResult.hasErrors()) {
            return "profiles/create_profile";
        }
        User user = userService.findByUsername(principal.getName());
        profile.setUser(user);
        profile = profileService.save(profile);
        return "redirect:/profiles/" + profile.getId() + "/edit";
    }


}
