package com.example.resume.service;

import com.example.resume.model.Image;
import com.example.resume.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image findByProfile_id(Long id) {
        return imageRepository.findByProfile_Id(id);
    }

    @Override
    public void clear(Long  id) {
        imageRepository.deleteByProfile_Id(id);
    }
}
