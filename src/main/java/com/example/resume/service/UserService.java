package com.example.resume.service;

import com.example.resume.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    boolean existsUserByUsername(String username);

    List<User> findAllUsers();

    void deleteById(Long id);

}
