package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.po.OrderHistory;

import java.util.List;

public interface OrderHistoryService {

    List<OrderHistory> getByOrderId(Long orderId);

    Long create(OrderHistory orderHistory);
}
