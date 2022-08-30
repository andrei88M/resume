package com.example.resume.service;

import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.repository.ProfileRepository;
import com.example.resume.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).orElseThrow();
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAllByUsername(String username) {
        return profileRepository.findAllByUser_Username(username);
    }

    @Override
    public void delete(Profile profile) {
        profileRepository.delete(profile);
    }

    @Override
    public void deleteById(Long id) {
        profileRepository.deleteById(id);
    }

}
