package com.jason.spy.financial.service;

import com.jason.spy.financial.dto.StockIndexResponse;

public interface StockIndexService {
    StockIndexResponse getStockIndexById(String id);
}
