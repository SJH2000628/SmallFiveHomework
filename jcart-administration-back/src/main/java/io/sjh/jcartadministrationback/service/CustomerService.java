package io.sjh.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.po.Customer;

public interface CustomerService {
    Page<Customer> search(Integer pageNum);

    Customer getById(Integer customerId);
}
