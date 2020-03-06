package io.sjh.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.sjh.jcartadministrationback.po.Customer;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerService {
    Page<Customer> search(Integer pageNum);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
