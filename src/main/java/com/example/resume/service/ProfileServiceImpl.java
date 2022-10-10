package com.example.resume.service;

import com.example.resume.model.Image;
import com.example.resume.model.Profile;
import com.example.resume.model.User;
import com.example.resume.repository.CellRepository;
import com.example.resume.repository.ImageRepository;
import com.example.resume.repository.ProfileRepository;
import com.example.resume.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    private final ProfileRepository profileRepository;

    private final ImageRepository imageRepository;

    @Override
    public Profile findById(Long id) {
        return profileRepository.findById(id).orElseThrow();
    }

    @Override
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile save(Profile profile, Image image) {
        Profile profile1 = profileRepository.save(profile);
        if (imageRepository.existsByProfile_Id(profile1.getId())) {
            Image image1 = imageRepository.findByProfile_Id(profile1.getId());
            imageRepository.delete(image1);
        }
        image.setProfile(profile);
        Image image1 = imageRepository.save(image);
        profile1.setImage(image1);
        return profile1;
    }

    @Override
    public Profile save(Profile profile, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return save(profile);
        }
        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setImageName(file.getName());
        return save(profile, image);
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

    @Override
    public Profile save(Profile profile, User user) {
        profile.setProfileName("name");
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    @Override
    public Profile save(Profile profile, User user, MultipartFile multipartFile) throws IOException {
        profile.setUser(user);
        return save(profile, multipartFile);
    }

    @Override
    public Profile save(Profile profile, String username) {
        if (profile.getProfileName() == null || profile.getProfileName().equals("")) {
            profile.setProfileName("name");
        }
        User user = userRepository.findByUsername(username);
        profile.setUser(user);
        return profileRepository.save(profile);
    }


}
