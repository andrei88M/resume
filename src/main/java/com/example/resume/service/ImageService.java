package com.example.resume.service;

import com.example.resume.model.Image;


public interface ImageService {

    Image findByProfile_id(Long id);

    void clear(Long id);
}
