package io.sjh.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.sjh.jcartadministrationback.dao.CustomerMapper;
import io.sjh.jcartadministrationback.po.Customer;
import io.sjh.jcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Page<Customer> search(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<Customer> page = customerMapper.search();
        return page;
    }

    @Override
    public Customer getById(Integer customerId) {
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        return customer;
    }
}
