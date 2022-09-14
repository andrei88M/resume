package com.example.resume.controller;

import com.example.resume.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class DeleteProfileController {

    private final ProfileService profileService;

    @PostMapping("/profiles/{id}/delete")
    public String deleteProfile(@PathVariable("id") Long id){
        profileService.deleteById(id);
        return "redirect:/profiles";
    }
}
