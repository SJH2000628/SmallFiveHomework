package io.sjh.jcartstoreback.service;

import io.sjh.jcartstoreback.po.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getByReturnId(Integer returnId);
}
