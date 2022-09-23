package com.example.resume.controller;

import com.example.resume.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class DeleteCellController {

    private final CellService cellService;

    @PostMapping("/profiles/{id}/{id_cell}/delete")
    private String delete(@PathVariable("id_cell") Long id_cell, @PathVariable("id") String id) {
        cellService.delete(id_cell);
        return "redirect:/profiles/" + id + "/edit";
    }
}
