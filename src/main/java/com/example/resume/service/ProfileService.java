package com.example.resume.service;

import com.example.resume.model.Profile;

import java.util.List;

public interface ProfileService {

    Profile findById(Long id);

    Profile save(Profile profile);

    List<Profile> findAllByUsername(String username);

    List<Profile> findAllByUser_id(Long id);

    void delete(Profile profile);

    void deleteById(Long id);
}
