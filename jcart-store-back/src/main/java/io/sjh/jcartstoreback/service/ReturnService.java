package io.sjh.jcartstoreback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartstoreback.po.Return;

public interface ReturnService {
    Integer create(Return aReturn);

    Page<Return> getPageCustomerId(Integer customerId,Integer pageNum);

    Return getById(Integer returnId);
}
