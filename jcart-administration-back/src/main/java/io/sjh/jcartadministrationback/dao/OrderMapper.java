package io.sjh.jcartadministrationback.dao;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.out.OrderListOutDTO;
import io.sjh.jcartadministrationback.po.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

//    custom

    Page<OrderListOutDTO> search(@Param("orderId") Long orderId,
                                 @Param("status") Byte status,
                                 @Param("startTime") Date startTime,
                                 @Param("endTime") Date endTime,
                                 @Param("totalPrice") Double totalPrice,
                                 @Param("customerName") String customerName);
}