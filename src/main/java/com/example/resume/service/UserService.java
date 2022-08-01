package com.example.resume.service;

import com.example.resume.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
