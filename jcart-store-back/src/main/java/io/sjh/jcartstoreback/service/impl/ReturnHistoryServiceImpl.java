package io.sjh.jcartstoreback.service.impl;

import io.sjh.jcartstoreback.dao.ReturnHistoryMapper;
import io.sjh.jcartstoreback.po.ReturnHistory;
import io.sjh.jcartstoreback.service.ReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {

    @Autowired
    private ReturnHistoryMapper returnHistoryMapper;
    @Override
    public List<ReturnHistory> getByReturnId(Integer returnId) {
        List<ReturnHistory> returnHistory = returnHistoryMapper.getByReturnId(returnId);
        return returnHistory;
    }
}
