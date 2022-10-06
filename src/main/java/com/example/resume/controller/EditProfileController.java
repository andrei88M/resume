package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.service.CellService;
import com.example.resume.service.ProfileService;
import com.example.resume.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class EditProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    private final CellService cellService;

    @GetMapping("/profiles/{id}/edit")
    public String edit(@PathVariable("id") Long id,
                       @ModelAttribute("cell") Cell cell,
                       Model model) {
        Profile profile = profileService.findById(id);
        log.info("profile_cell " + profile.getCells().size());
        model.addAttribute("profile", profile);

        return "profiles/profile_id/edit_profile";
    }

    @PostMapping("/profiles/{id}/edit")
    public String editProfile(@ModelAttribute("profile") Profile profile,
                              @PathVariable("id") Long id,
                              @RequestParam("file") MultipartFile multipartFile,
                              Principal principal) throws IOException {
        log.info("profile_id " + profile.getId());
        profile.setId(id);
        profile.setUser(userService.findByUsername(principal.getName()));
        profileService.save(profile, multipartFile);
        return "redirect:/profiles";
    }

    @PostMapping("/profiles/{id}/edit/{id_cell}/edit-cell")
    public String editCell(@ModelAttribute("cell") Cell cell,
                           @PathVariable("id") Long id, @PathVariable Long id_cell) {
        log.info("id = " + cell.getId() + " name = " + cell.getCellName() + " text = " + cell.getText() + " type = " + cell.isType());

        cell.setId(id_cell);
        Profile profile = profileService.findById(id);
        cell.setProfile(profile);
        cellService.save(cell);
        return "redirect:/profiles/" + id + "/edit";
    }
}
