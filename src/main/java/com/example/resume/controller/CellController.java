package com.example.resume.controller;

import com.example.resume.model.Cell;
import com.example.resume.service.CellService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @PostMapping("/profiles/{id}/cells")
    public String createCell(@PathVariable("id") Long id, @ModelAttribute Cell cell) {
        Cell cell1 = cellService.save(cell, id);
        return "redirect:/profiles/" + cell1.getProfile().getId();
    }

    @PatchMapping("/profiles/{id}/cells/{id_cell}")
    public String editCell(@ModelAttribute("cell") Cell cell,
                           @PathVariable("id") Long id,
                           @PathVariable Long id_cell) {
        cellService.save(cell, id, id_cell);
        return "redirect:/profiles/" + id;
    }

    @DeleteMapping("/profiles/{id}/cells/{id_cell}")
    public String delete(@PathVariable("id_cell") Long idCell, @PathVariable("id") String id) {
        cellService.delete(idCell);
        return "redirect:/profiles/" + id;
    }
}
