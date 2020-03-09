package io.sjh.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.ReturnSearchInDTO;
import io.sjh.jcartadministrationback.dto.in.ReturnUpdateActionInDTO;
import io.sjh.jcartadministrationback.dto.out.PageOutDTO;
import io.sjh.jcartadministrationback.dto.out.ReturnListOutDTO;
import io.sjh.jcartadministrationback.dto.out.ReturnShowOutDTO;
import io.sjh.jcartadministrationback.po.Return;
import io.sjh.jcartadministrationback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/return")
@CrossOrigin
public class ReturnController {

    @Autowired
    private ReturnService returnService;
    @GetMapping("/search")
    public PageOutDTO<ReturnListOutDTO> search(ReturnSearchInDTO returnSearchInDTO,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Return> page = returnService.search(pageNum);
        List<ReturnListOutDTO> returnListOutDTOS = page.stream().map(aReturn -> {
            ReturnListOutDTO returnListOutDTO = new ReturnListOutDTO();
            returnListOutDTO.setReturnId(aReturn.getReturnId());
            returnListOutDTO.setOrderId(aReturn.getOrderId());
            returnListOutDTO.setCustomerId(aReturn.getCustomerId());
            returnListOutDTO.setCustomerName(aReturn.getCustomerName());
            returnListOutDTO.setProductCode(aReturn.getProductCode());
            returnListOutDTO.setProductName(aReturn.getProductName());
            returnListOutDTO.setStatus(aReturn.getStatus());
            returnListOutDTO.setCreateTimestamp(aReturn.getCreateTime().getTime());
            returnListOutDTO.setUpdateTimestamp(aReturn.getUpdateTime().getTime());
            return returnListOutDTO;

        }).collect(Collectors.toList());
        PageOutDTO<ReturnListOutDTO> returnListOutDTOPageOutDTO = new PageOutDTO<>();
        returnListOutDTOPageOutDTO.setTotal(page.getTotal());
        returnListOutDTOPageOutDTO.setPageSize(page.getPageSize());
        returnListOutDTOPageOutDTO.setPageName(pageNum);
        returnListOutDTOPageOutDTO.setList(returnListOutDTOS);
        return returnListOutDTOPageOutDTO;
    }
    @GetMapping("/getById")
    public ReturnShowOutDTO getById(@RequestParam Integer returnId){

        return null;
    }

    @PostMapping("/updateAction")
    public void updateAction(@RequestBody ReturnUpdateActionInDTO returnUpdateActionInDTO){

    }
}
