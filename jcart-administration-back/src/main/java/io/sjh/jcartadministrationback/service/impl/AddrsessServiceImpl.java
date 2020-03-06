package io.sjh.jcartadministrationback.service.impl;

import io.sjh.jcartadministrationback.dao.AddressMapper;
import io.sjh.jcartadministrationback.po.Address;
import io.sjh.jcartadministrationback.service.AddrsessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddrsessServiceImpl implements AddrsessService {
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public Address getById(Integer addressId) {
        Address address = addressMapper.selectByPrimaryKey(addressId);
        return address;
    }
}
