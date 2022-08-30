package com.example.resume.repository;

import com.example.resume.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {

    List<Cell> findAllByProfile_Id(Long id);

}
