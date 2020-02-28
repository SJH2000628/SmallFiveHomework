package io.sjh.jcartadministrationback.service;

import io.sjh.jcartadministrationback.dto.in.ProductCreateInDTO;

public interface ProductService {
    Integer create(ProductCreateInDTO productCreateInDTO);
}
