package io.sjh.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.sjh.jcartadministrationback.dao.OrderDetailMapper;
import io.sjh.jcartadministrationback.dao.OrderMapper;
import io.sjh.jcartadministrationback.dto.out.OrderListOutDTO;
import io.sjh.jcartadministrationback.po.OrderDetail;
import io.sjh.jcartadministrationback.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderServiceImpl implements OderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public Page<OrderListOutDTO> search(Integer pageNum) {

        PageHelper.startPage(pageNum, 10);
        Page<OrderListOutDTO> page = orderMapper.search();
        return page;
    }
}
