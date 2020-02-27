package io.sjh.jcartadministrationback.controller;

import io.sjh.jcartadministrationback.dto.out.AddressListOutDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @GetMapping("/getListCustomerId")
    public List<AddressListOutDTO> getListCustomerId(@RequestParam Integer customerId){

        return null;
    }
}
