package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.service.CellService;
import com.example.resume.service.ProfileService;
import com.example.resume.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('USER')")
@Slf4j
public class CreateProfileController {

    private final ProfileService profileService;

    private final CellService cellService;

    private final UserService userService;

    private Profile tempProfile;

    public CreateProfileController(ProfileService profileService, CellService cellService, UserService userService) {
        this.profileService = profileService;
        this.cellService = cellService;
        this.userService = userService;
    }

    @GetMapping("/profiles/create-profile")
    public String create(@ModelAttribute("cell") Cell cell, Model model) {
        log.info(cell.getClass().getName());
        if (tempProfile == null) {
            tempProfile = new Profile();
            tempProfile.setCells(new ArrayList<>());
        }
        model.addAttribute("profile", tempProfile);
        return "profiles/create_profile";
    }

    @PostMapping("/profiles/create-profile")
    public String createProfile(@ModelAttribute("profile") @Valid Profile profile,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) {
        log.info(profile.getProfileName() + " " + profile.getStatus());
        if (bindingResult.hasErrors()) {
            log.info("error profile");
            model.addAttribute("cell", new Cell());
            //model.addAttribute("profile", tempProfile);
            return "profiles/create_profile";
        }
        User user = userService.findByUsername(principal.getName());
        profile.setUser(user);
        profile = profileService.save(profile);
        List<Cell> cells = tempProfile.getCells();
        for (Cell cell : cells) {
            cell.setProfile(profile);
        }
        cellService.saveAll(cells);
        tempProfile = null;
        return "redirect:/profiles";
    }

    @PostMapping("/profiles/create-cell")
    public String createCell(@ModelAttribute("cell") @Valid Cell cell, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("profile", tempProfile);
            return "profiles/create_profile";
        }
        tempProfile.getCells().add(cell);
        log.info(String.valueOf(tempProfile.getCells().size()));
        return "redirect:/profiles/create-profile";
    }
}
