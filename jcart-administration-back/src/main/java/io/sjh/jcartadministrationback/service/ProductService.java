package io.sjh.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.sjh.jcartadministrationback.dto.in.ProductUpdateInDTO;
import io.sjh.jcartadministrationback.dto.out.ProductListOutDTO;
import io.sjh.jcartadministrationback.dto.out.ProductShowOutDTO;

import java.util.List;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);
    void update(ProductUpdateInDTO productUpdateInDTO);
    void delete(Integer productId);
    void batchDelete(List<Integer> productIds);
    Page<ProductListOutDTO> search(Integer pageNum);
    ProductShowOutDTO getById(Integer productId);
}
