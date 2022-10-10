package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.service.CellService;
import com.example.resume.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profiles")
    public String viewProfiles(@AuthenticationPrincipal String username,
                               @ModelAttribute("profile") Profile profile,
                               Model model) {
        List<Profile> profiles = profileService.findAllByUsername(username);
        model.addAttribute("profiles", profiles);
        return "profiles";
    }

    @PostMapping("/profiles")
    public String createProfile(@AuthenticationPrincipal String username,
                                @ModelAttribute("profile") Profile profile) {
        profile = profileService.save(profile, username);
        return "redirect:/profiles/" + profile.getId();
    }

    @GetMapping("/profiles/{id}")
    public String viewProfile(@PathVariable("id") Long id, Model model) {
        Profile profile = profileService.findById(id);
        model.addAttribute("profile", profile);
        return "profiles/edit_profile";
    }

    @PatchMapping("/profiles/{id}")
    public String editProfile(@ModelAttribute("profile") Profile profile,
                              @AuthenticationPrincipal User user,
                              @PathVariable("id") Long id,
                              @RequestParam("file") MultipartFile multipartFile) throws IOException {
        profile.setId(id);
        profile = profileService.save(profile, user, multipartFile);
        return "redirect:/profiles/" + profile.getId();
    }

    @DeleteMapping("/profiles/{id}")
    public String deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteById(id);
        return "redirect:/profiles";
    }
}
