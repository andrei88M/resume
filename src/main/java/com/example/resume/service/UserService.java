package com.example.resume.service;

import com.example.resume.model.User;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    boolean existsUserByUsername(String username);

}
