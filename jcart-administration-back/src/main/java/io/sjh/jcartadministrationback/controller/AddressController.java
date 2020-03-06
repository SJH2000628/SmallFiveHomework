package io.sjh.jcartadministrationback.controller;

import io.sjh.jcartadministrationback.dto.out.AddressListOutDTO;
import io.sjh.jcartadministrationback.dto.out.AddressShowOutDTO;
import io.sjh.jcartadministrationback.po.Address;
import io.sjh.jcartadministrationback.service.AddrsessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddrsessService addrsessService;
    @GetMapping("/getListCustomerId")
    public List<AddressListOutDTO> getListCustomerId(@RequestParam Integer customerId){

        return null;
    }

    @GetMapping("/getById")
    public AddressShowOutDTO getById(@RequestParam Integer addressId){
        Address address = addrsessService.getById(addressId);
        AddressShowOutDTO addressShowOutDTO = new AddressShowOutDTO();
        addressShowOutDTO.setAddressId(addressId);
        addressShowOutDTO.setContent(address.getContent());
        addressShowOutDTO.setReceiverMobile(address.getReceiverMobile());
        addressShowOutDTO.setReceiverName(address.getReceiverName());
        addressShowOutDTO.setTag(address.getTag());

        return addressShowOutDTO;
    }
}
