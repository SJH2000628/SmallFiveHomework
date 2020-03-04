package io.sjh.jcartstoreback.service;

import io.sjh.jcartstoreback.dto.in.OrderCheckoutInDTO;

public interface OrderService {
    Long createkout(OrderCheckoutInDTO orderCheckoutInDTO,
                       Integer customerId);


}
