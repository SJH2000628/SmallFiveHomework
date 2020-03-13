package io.sjh.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.OrderSearchInDTO;
import io.sjh.jcartadministrationback.dto.out.OrderListOutDTO;
import io.sjh.jcartadministrationback.dto.out.OrderShowOutDTO;
import io.sjh.jcartadministrationback.po.Order;

public interface OderService {

    Page<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO,Integer pageNum);

    OrderShowOutDTO getById(Long orderId);

}
