package io.sjh.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.sjh.jcartadministrationback.dto.in.ProductSearchInDTO;
import io.sjh.jcartadministrationback.dto.in.ProductUpdateInDTO;
import io.sjh.jcartadministrationback.dto.out.PageOutDTO;
import io.sjh.jcartadministrationback.dto.out.ProductListOutDTO;
import io.sjh.jcartadministrationback.dto.out.ProductShowOutDTO;
import io.sjh.jcartadministrationback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @Value("${www.image.baseurl}")
    private String imageBaseurl;

    @GetMapping("/search")
    public PageOutDTO<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,
                                                @RequestParam(required = false, defaultValue = "1") Integer pageName) {
        Page<ProductListOutDTO> page = productService.search(productSearchInDTO, pageName);
//        for (ProductListOutDTO outDTO:page) {
//            outDTO.setMainPicUrl(imageBaseurl+"/"+outDTO.getMainPicUrl());
//        }
        PageOutDTO<ProductListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageName(page.getPageNum());
        pageOutDTO.setList(page);
        return pageOutDTO;
    }

    @GetMapping("/getById")
    public ProductShowOutDTO getById(@RequestParam Integer productId) {
        ProductShowOutDTO productShowOutDTO = productService.getById(productId);
        return productShowOutDTO;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody ProductCreateInDTO productCreateInDTO) {
        Integer productId = productService.create(productCreateInDTO);
        return productId;
    }

    @PostMapping("/update")
    public void update(@RequestBody ProductUpdateInDTO productUpdateInDTO) {

        productService.update(productUpdateInDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer productId) {

        productService.delete(productId);
    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> productIds) {
        productService.batchDelete(productIds);
    }
}
