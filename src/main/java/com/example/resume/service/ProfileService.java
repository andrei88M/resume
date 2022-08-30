package com.example.resume.service;

import com.example.resume.model.Profile;

import java.util.List;
import java.util.Set;

public interface ProfileService {

    Profile findById(Long id);

    Profile save(Profile profile);

    List<Profile> findAllByUsername(String username);

    void delete(Profile profile);

    void deleteById(Long id);
}
