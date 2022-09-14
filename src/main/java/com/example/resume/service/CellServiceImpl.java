package com.example.resume.service;

import com.example.resume.model.Cell;
import com.example.resume.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CellServiceImpl implements CellService {

    private final CellRepository cellRepository;

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
}
