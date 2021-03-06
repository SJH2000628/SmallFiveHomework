package io.sjh.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.dto.in.ReturnSearchInDTO;
import io.sjh.jcartadministrationback.po.Return;

public interface ReturnService {
    Page<Return> search(ReturnSearchInDTO returnSearchInDTO, Integer pageNum);
    Return getById(Integer returnId);
    void update(Return aReturn);
}
