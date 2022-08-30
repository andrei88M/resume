package com.example.resume.service;

import com.example.resume.model.Cell;

import java.util.List;

public interface CellService {

    List<Cell> findAllByProfile_Id(Long id);

    List<Cell> saveAll(List<Cell> cells);

    Cell save(Cell cell);

    Cell findById(Long cell_id);
}
