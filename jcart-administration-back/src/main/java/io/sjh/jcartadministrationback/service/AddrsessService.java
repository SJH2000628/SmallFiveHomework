package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.po.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddrsessService {

    Address getById(Integer addressId);
    List<Address> selectByCustomerId(Integer customerId);
}
