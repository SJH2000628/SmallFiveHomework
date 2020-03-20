package io.sjh.jcartadministrationback.controller;

import io.sjh.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.sjh.jcartadministrationback.dto.out.AdministratorLoginOutDTO;
import io.sjh.jcartadministrationback.exception.ClientException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AdministratorControllerTest {

    @Autowired
    private AdministratorController administratorController;
    @Test
    void login() throws ClientException {
        AdministratorLoginInDTO administratorLoginInDTO = new AdministratorLoginInDTO();
        administratorLoginInDTO.setUsername("song");
        administratorLoginInDTO.setPassword("123456");
        AdministratorLoginOutDTO logins = administratorController.login(administratorLoginInDTO);
        assertNotNull(logins);
        assertEquals(administratorLoginInDTO,logins);
    }
}