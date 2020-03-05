package io.sjh.jcartstoreback.service;

import io.sjh.jcartstoreback.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistory> getByOrderId(Long orderId);

}
