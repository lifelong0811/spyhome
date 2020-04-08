package com.jason.spy.financial.dto;

import com.jason.spy.financial.constant.BaseResponseEnum;
import lombok.Data;

@Data
public class StockIndexResponse {
    private StockData data;
    private int errorCode;
    private String errorDescription;
    BaseResponse baseResponse;

    public static StockIndexResponse buildFailure(BaseResponseEnum baseResponseEnum,
                                                   String outterCode,
                                                   String outterMsg) {
        StockIndexResponse response = new StockIndexResponse();
        response.setBaseResponse(BaseResponse.buidFailure(
                baseResponseEnum, outterCode, outterMsg
        ));
        return response;
    }
}

