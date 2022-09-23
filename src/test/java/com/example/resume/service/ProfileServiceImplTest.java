package com.example.resume.service;

import com.example.resume.model.Image;
import com.example.resume.model.Profile;
import com.example.resume.model.Status;
import com.example.resume.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProfileServiceImplTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @BeforeEach
    @AfterEach
    void resetDB() {
        profileRepository.deleteAll();
    }

    @Test
    void save() {
        Profile profile = profileService.save(createTestProfile(), new Image());
        log.info("profile_id " + profile.getId() + " image_id " + profile.getImage().getId());
    }

    @Test
    void findById(){
        Profile profile = profileService.save(createTestProfile());
        profile = profileService.findById(profile.getId());
        log.info(profile.toString());
    }

    Profile createTestProfile() {
        return Profile.builder()
                .profileName("profile")
                .status(Status.PUBLIC)
                .build();
    }
}