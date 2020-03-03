package io.sjh.jcartstoreback.service;

import io.sjh.jcartstoreback.dto.in.CustomerRegisterInDTO;
import io.sjh.jcartstoreback.po.Customer;

public interface CustomerService {

    Integer register(CustomerRegisterInDTO customerRegisterInDTO);

    Customer getByUsername(String username);

    Customer getById(Integer customerId);

    Customer getByEmail(String email);

    void update(Customer customer);


}
