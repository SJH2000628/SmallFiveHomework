package io.sjh.jcartstoreback.service.impl;

import io.sjh.jcartstoreback.dao.ReturnMapper;
import io.sjh.jcartstoreback.po.Return;
import io.sjh.jcartstoreback.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private ReturnMapper returnMapper;
    @Override
    public Integer create(Return aReturn) {
        returnMapper.insertSelective(aReturn);
        Integer returnId = aReturn.getReturnId();
        return returnId;

    }
}
