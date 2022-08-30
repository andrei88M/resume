package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.service.CellService;
import com.example.resume.service.ProfileService;
import com.example.resume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
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
        if (tempProfile == null){
            tempProfile = new Profile();
            tempProfile.setCells(new ArrayList<>());
        }
        model.addAttribute("profile", tempProfile);
        return "profiles/create_profile";
    }

    @PostMapping("/profiles/create-profile")
    public String createProfile(@ModelAttribute("profile") Profile profile,
                                Principal principal,
                                Model model) {
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
    public String createCell(@ModelAttribute("cell") Cell cell) {
        tempProfile.getCells().add(cell);
        System.out.println(1 + "   "+tempProfile.getCells());
        return "redirect:/profiles/create-profile";
    }
}
