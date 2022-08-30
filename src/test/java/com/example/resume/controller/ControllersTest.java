package com.example.resume.controller;;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ControllersTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void loginControllerTest() throws Exception {
        Assertions.assertThat(loginController).isNotNull();
    }
}