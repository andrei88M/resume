package com.example.resume.repository;

import com.example.resume.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CellRepository cellRepository;

    @BeforeEach
    void resetDB() {
        cellRepository.deleteAll();
        profileRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByUsername() {
        userRepository.save(createTestUser());
        User user = userRepository.findByUsername("user");
        System.err.println(user);
    }

    @Test
    void existsByUsername() {
        userRepository.save(createTestUser());
        boolean bool = userRepository.existsByUsername("user");
        System.err.println(bool);
    }

    @Test
    void findAllByUser_Username() {
        User user = createTestUser();
        Profile profile = createTestProfile();
        user = userRepository.save(user);
        profile.setUser(user);
        profileRepository.save(profile);
        List<Profile> list = profileRepository.findAllByUser_Username("user");
        System.err.println(list);
    }

    @Test
    void findAllByProfile_Id(){
        User user = createTestUser();
        Profile profile = createTestProfile();
        Cell cell = createTestCell();
        Cell cell2 = createTestCell();

        User user1 = userRepository.save(user);
        profile.setUser(user1);
        Profile profile1 = profileRepository.save(profile);

        cell.setProfile(profile1);
        cell2.setProfile(profile1);

        cellRepository.save(cell);
        cellRepository.save(cell2);

        List<Cell> list = cellRepository.findAllByProfile_Id(profile1.getId());
        System.err.println(list);
    }

    @Test
    void saveProfileTest(){
        Profile profile = createTestProfile();
        Cell cell = createTestCell();
        profile.setCells(List.of(cell));
        profile = profileRepository.save(profile);
        System.err.println(profile.getCells());
    }

    User createTestUser() {
        return User.builder()
                .username("user")
                .password("password")
                .password2("password2")
                .roles(Set.of(Role.USER))
                .build();
    }

    Profile createTestProfile() {
        return Profile.builder()
                .profileName("profile")
                .status(Status.PUBLIC)
                .build();
    }

    Cell createTestCell(){
        return Cell.builder()
                .text("text")
                .cellName("cell")
                .build();
    }
}