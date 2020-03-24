package io.sjh.jcartstoreback.service;


import com.github.pagehelper.Page;
import io.sjh.jcartstoreback.dto.in.ProductSearchInDTO;
import io.sjh.jcartstoreback.dto.out.ProductListOutDTO;
import io.sjh.jcartstoreback.dto.out.ProductShowOutDTO;
import io.sjh.jcartstoreback.dto.out.ProductShowOutDTO;
import io.sjh.jcartstoreback.po.Product;

public interface ProductService {

    Product getById(Integer productId);

    ProductShowOutDTO getShowById(Integer productId);

    Page<ProductListOutDTO> search(ProductSearchInDTO productSearchInDTO,Integer pageNum);

}
