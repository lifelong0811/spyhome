package com.jason.spy.financial.service;

import com.jason.spy.financial.constant.BaseResponseEnum;
import com.jason.spy.financial.dto.BaseResponse;
import com.jason.spy.financial.dto.StockIndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class StockIndexServiceImpl implements StockIndexService {
    public static final String STOCK_INDEX_URL = "https://stock.xueqiu.com/v5/stock/quote.json?symbol=%s&extend=detail";
    public static final Logger LOGGER = LoggerFactory.getLogger(StockIndexServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public StockIndexResponse getStockIndexById(String id) {

        StockIndexResponse response = null;
        try {
            response = restTemplate.getForObject(String.format(STOCK_INDEX_URL, id), StockIndexResponse.class);
        } catch (Exception e) {
            LOGGER.error("getStockIndexById exception, id: {}", id, e);
            return StockIndexResponse.buildFailure(BaseResponseEnum.SYSTEM_EXCEPTION, "getStockIndexById抛出异常", e.getMessage());
        }
        if (response == null) {
            return StockIndexResponse.buildFailure(BaseResponseEnum.OUTTER_ERROR, "getStockIndexById http请求返回为空", "id为: " + id);
        }
        if (response.getErrorCode() != 0) {
            return StockIndexResponse.buildFailure(BaseResponseEnum.OUTTER_ERROR, String.valueOf(response.getErrorCode()), response.getErrorDescription());
        }
        response.setBaseResponse(BaseResponse.buidSuccess());
        return response;
    }
}
