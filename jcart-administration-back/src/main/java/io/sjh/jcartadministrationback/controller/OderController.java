package io.sjh.jcartadministrationback.controller;

import io.sjh.jcartadministrationback.dto.in.OrderSearchInDTO;
import io.sjh.jcartadministrationback.dto.out.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OderController {
    @GetMapping("/search")
    public PageOutDTO<OrderListOutDTO> search(OrderSearchInDTO orderSearchInDTO,
                                              @RequestParam Integer pageName){

        return null;
    }

    @GetMapping("/getById")
    public OrderShowOutDTO getById(@RequestParam Long orderId){
        return null;
    }

    @GetMapping("/getInvoiceInfo")
    public OrderInvoiceShowOutDTO getInvoiceInfo(@RequestParam Long orderId){
        return null;
    }


    @GetMapping("/getShipInfo")
    public OrderShipShowOutDTO getShipInfo(@RequestParam Long orderId){

        return null;

    }
}
