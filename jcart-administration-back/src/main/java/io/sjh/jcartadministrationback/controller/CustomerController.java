package io.sjh.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.CustomerSearchInDTO;
import io.sjh.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.sjh.jcartadministrationback.dto.out.CustomerListOutDTO;
import io.sjh.jcartadministrationback.dto.out.CustomerShowOutDTO;
import io.sjh.jcartadministrationback.dto.out.PageOutDTO;
import io.sjh.jcartadministrationback.po.Address;
import io.sjh.jcartadministrationback.po.Customer;
import io.sjh.jcartadministrationback.service.AddrsessService;
import io.sjh.jcartadministrationback.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddrsessService addrsessService;
    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(CustomerSearchInDTO customerSearchInDTO,
                                                 @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Customer> page = customerService.search(customerSearchInDTO,pageNum);
        List<CustomerListOutDTO> customerListOutDTOS = page.stream().map(customer -> {
            CustomerListOutDTO customerListOutDTO = new CustomerListOutDTO();
            customerListOutDTO.setCustomerId(customer.getCustomerId());
            customerListOutDTO.setUsername(customer.getUsername());
            customerListOutDTO.setRealName(customer.getRealName());
            customerListOutDTO.setMobile(customer.getMobile());
            customerListOutDTO.setEmail(customer.getEmail());
            customerListOutDTO.setStatus(customer.getStatus());
            customerListOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
            return customerListOutDTO;
        }).collect(Collectors.toList());
        PageOutDTO<CustomerListOutDTO> pageOutDTO = new PageOutDTO<>();

        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageName(pageNum);
        pageOutDTO.setList(customerListOutDTOS);
        return pageOutDTO;
    }

    @GetMapping("/getById")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        Customer customer = customerService.getById(customerId);
        CustomerShowOutDTO customerShowOutDTO = new CustomerShowOutDTO();
        customerShowOutDTO.setCustomerId(customerId);
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setStatus(customer.getStatus());
        customerShowOutDTO.setRewordPoints(customer.getRewordPoints());
        customerShowOutDTO.setNewsSubscribed(customer.getNewsSubscribed());
        customerShowOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
        customerShowOutDTO.setDefaultAddressId(customer.getDefaultAddressId());
        Address defaultAddress = addrsessService.getById(customer.getDefaultAddressId());
        if (defaultAddress!=null){
            customerShowOutDTO.setDefaultAddress(defaultAddress.getContent());
        }
        return customerShowOutDTO;
    }

    @PostMapping("/setStatus")
    public void setStatus(@RequestBody CustomerSetStatusInDTO customerSetStatusInDTO){
    customerService.setStatus(customerSetStatusInDTO);
    }
}
