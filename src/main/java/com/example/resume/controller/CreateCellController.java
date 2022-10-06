package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.repository.ProfileRepository;
import com.example.resume.service.CellService;
import com.example.resume.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CreateCellController {

    private final CellService cellService;

    private final ProfileService profileService;

    @PostMapping("/profiles/{id}/create_cell")
    private String createCell(@PathVariable("id") Long id, @ModelAttribute Cell cell) {
        log.info("id " + id);
        Profile profile = profileService.findById(id);
        log.info("profile " + profile + " cell " + cell);
        cell.setProfile(profile);
        cellService.save(cell);
        return "redirect:/profiles/" + id + "/edit";
    }
}
