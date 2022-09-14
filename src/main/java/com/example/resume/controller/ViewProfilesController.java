package com.example.resume.controller;

import com.example.resume.model.Profile;
import com.example.resume.model.Status;
import com.example.resume.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewProfilesController {

    private final ProfileService profileService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/profiles")
    public String viewProfiles(Principal principal, Model model) {
        String username = principal.getName();
        List<Profile> profiles = profileService.findAllByUsername(username);
        model.addAttribute("profiles", profiles);
        return "profiles";
    }

    @GetMapping("/profiles/{id}")
    public String viewProfile(@PathVariable("id") Long id, Model model) {
        Profile profile = profileService.findById(id);
        model.addAttribute("profile", profile);
        if (profile.getStatus().equals(Status.PRIVAT)) {
            return "forward:/profiles/privat-profile";
        }
        return "profiles/profile_id";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/profiles/privat-profile")
    public String viewProfile() {
        return "profiles/profile_id";
    }
}
