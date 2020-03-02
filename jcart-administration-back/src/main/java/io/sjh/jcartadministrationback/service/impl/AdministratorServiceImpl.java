package io.sjh.jcartadministrationback.service.impl;

import io.sjh.jcartadministrationback.dao.AdministratorMapper;
import io.sjh.jcartadministrationback.po.Administrator;
import io.sjh.jcartadministrationback.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public Administrator getByUsername(String username) {
        Administrator administrator = administratorMapper.selectByUsername(username);
        return administrator;
    }
}
