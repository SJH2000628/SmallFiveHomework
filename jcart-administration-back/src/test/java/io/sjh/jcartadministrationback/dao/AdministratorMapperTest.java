package io.sjh.jcartadministrationback.dao;

import io.sjh.jcartadministrationback.po.Administrator;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AdministratorMapperTest {

    @Autowired
    private AdministratorMapper administratorMapper;

    @Test
    void selectByUsername() {
        String username="song";
        Administrator administrator = administratorMapper.selectByUsername(username);
        assertNotNull(administrator);
        Administrator administratorx = administratorMapper.selectByUsername(username);
        assertEquals(username,administratorx.getUsername());
    }
}