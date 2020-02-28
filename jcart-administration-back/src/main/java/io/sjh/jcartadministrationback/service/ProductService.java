package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.sjh.jcartadministrationback.dto.in.ProductUpdateInDTO;

import java.util.List;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);
    void update(ProductUpdateInDTO productUpdateInDTO);
    void delete(Integer productId);
    void batchDelete(List<Integer> productIds);
}
