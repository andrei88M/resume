package com.example.resume.repository;

import com.example.resume.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByProfile_Id(Long id);

    void deleteByProfile_Id(Long id);

    void deleteAllByProfile_Id(Long id);

    boolean existsByProfile_Id(Long id);
}
