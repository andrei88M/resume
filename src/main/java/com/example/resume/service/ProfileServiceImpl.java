package com.example.resume.service;

import com.example.resume.model.Profile;
import com.example.resume.repository.ProfileRepository;
import com.example.resume.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

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
    public List<Profile> findAllByUser_id(Long id) {
        return profileRepository.findAllByUser_Id(id);
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
