package com.example.resume.service;

import com.example.resume.model.Image;
import com.example.resume.model.Profile;
import com.example.resume.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {

    Profile findById(Long id);

    Profile save(Profile profile);

    Profile save(Profile profile, Image image);

    Profile save(Profile profile, MultipartFile file) throws IOException;

    List<Profile> findAllByUsername(String username);

    List<Profile> findAllByUser_id(Long id);

    void delete(Profile profile);

    void deleteById(Long id);

    Profile save(Profile profile, User user);

    Profile save(Profile profile, User user, MultipartFile multipartFile) throws IOException;

    Profile save(Profile profile, String username);
}
