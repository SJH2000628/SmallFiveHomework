package io.sjh.jcartadministrationback.service.impl;

import io.sjh.jcartadministrationback.po.Administrator;
import io.sjh.jcartadministrationback.service.AdministratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdministratorServiceImplTest {

    @Autowired
    private AdministratorService administratorService;

    @Test
    void getByUsername() {
        String username="song";
        Administrator byUsername = administratorService.getByUsername(username);
        assertNotNull(byUsername);
        assertEquals(username,byUsername.getUsername());
    }
}