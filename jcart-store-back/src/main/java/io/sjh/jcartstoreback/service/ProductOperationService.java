package io.sjh.jcartstoreback.service;

import io.sjh.jcartstoreback.po.ProductOperation;

import java.util.List;

public interface ProductOperationService {
    void count(Integer productId);
    List<ProductOperation> selectHotProduct();
}
