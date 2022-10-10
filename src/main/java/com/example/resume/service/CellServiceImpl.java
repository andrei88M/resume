package com.example.resume.service;

import com.example.resume.model.Cell;
import com.example.resume.model.Profile;
import com.example.resume.repository.CellRepository;
import com.example.resume.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CellServiceImpl implements CellService {

    private final CellRepository cellRepository;

    private final ProfileRepository profileRepository;

    @Override
    public List<Cell> findAllByProfile_Id(Long id) {
        return cellRepository.findAllByProfile_Id(id);
    }

    @Override
    public List<Cell> saveAll(List<Cell> cells) {
        return cellRepository.saveAll(cells);
    }

    @Override
    public Cell save(Cell cell) {
        return cellRepository.save(cell);
    }

    @Override
    public Cell findById(Long cell_id) {
        return cellRepository.findById(cell_id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        cellRepository.deleteById(id);
    }

    @Override
    public Cell save(Cell cell, Long profileId) {
        Optional<Profile> profileOptional = profileRepository.findById(profileId);
        Profile profile = profileOptional.orElseThrow();
        cell.setProfile(profile);
        return cellRepository.save(cell);
    }

    @Override
    public Cell save(Cell cell, Long id, Long id_cell) {
        cell.setId(id_cell);
        Profile profile = profileRepository.findById(id).orElseThrow();
        cell.setProfile(profile);
        return cellRepository.save(cell);
    }
}
