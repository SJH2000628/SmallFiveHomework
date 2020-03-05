package io.sjh.jcartstoreback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartstoreback.dto.in.OrderCheckoutInDTO;
import io.sjh.jcartstoreback.dto.out.OrderShowOutDTO;
import io.sjh.jcartstoreback.po.Order;

public interface OrderService {
    Long createkout(OrderCheckoutInDTO orderCheckoutInDTO,
                       Integer customerId);


    Page<Order> getByCustomerId(Integer pageNum, Integer customerId);

    OrderShowOutDTO getById(Long orderId);
}
