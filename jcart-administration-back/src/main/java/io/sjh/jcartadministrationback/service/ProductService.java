package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.dto.in.ProductCreateInDTO;
import io.sjh.jcartadministrationback.dto.in.ProductUpdateInDTO;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);
    void update(ProductUpdateInDTO productUpdateInDTO);
}
