package io.sjh.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.OrderSearchInDTO;
import io.sjh.jcartadministrationback.dto.out.*;
import io.sjh.jcartadministrationback.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OderController {
    @Autowired
    private OderService oderService;
    @GetMapping("/search")
    public PageOutDTO<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO,
                                              @RequestParam(required = false, defaultValue = "1") Integer pageName) {
        Page<OrderListOutDTO> page = oderService.search(orderSearchInDTO,pageName);
        PageOutDTO<OrderListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageName(page.getPageNum());
        pageOutDTO.setList(page);
        return pageOutDTO;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId) {
        OrderShowOutDTO orderShowOutDTO = oderService.getById(orderId);
        return orderShowOutDTO;
    }

    @GetMapping("/getInvoiceInfo")
    public OrderInvoiceShowOutDTO getInvoiceInfo(@RequestParam Long orderId) {
        return null;
    }


    @GetMapping("/getShipInfo")
    public OrderShipShowOutDTO getShipInfo(@RequestParam Long orderId) {

        return null;

    }
}
