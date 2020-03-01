package io.sjh.jcartstoreback.service;


import com.github.pagehelper.Page;
import io.sjh.jcartstoreback.dto.out.ProductListOutDTO;
import io.sjh.jcartstoreback.dto.out.ProductShowOutDTO;
import io.sjh.jcartstoreback.dto.out.ProductShowOutDTO;

public interface ProductService {

    ProductShowOutDTO getById(Integer productId);

    Page<ProductListOutDTO> search(Integer pageNum);

}
