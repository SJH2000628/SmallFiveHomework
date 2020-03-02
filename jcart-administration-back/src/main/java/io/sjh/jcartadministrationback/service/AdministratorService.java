package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.po.Administrator;

public interface AdministratorService {

    Administrator getByUsername(String username);
}
