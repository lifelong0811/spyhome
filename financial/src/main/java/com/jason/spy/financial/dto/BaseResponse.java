package com.jason.spy.financial.dto;

import com.jason.spy.financial.constant.BaseResponseEnum;
import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String message;
    private String outterCode;
    private String outterMessage;


    public static BaseResponse buidSuccess() {
        BaseResponse response = new BaseResponse();
        response.setCode(BaseResponseEnum.SUCCESS.getCode());
        response.setMessage(BaseResponseEnum.SUCCESS.getMessage());
        return response;
    }

    public static BaseResponse buidFailure(BaseResponseEnum baseResponseEnum,
                                           String outterCode,
                                           String outterMessage) {
        BaseResponse response = new BaseResponse();
        response.setCode(baseResponseEnum.getCode());
        response.setMessage(baseResponseEnum.getMessage());
        response.setOutterCode(outterCode);
        response.setOutterMessage(outterMessage);
        return response;
    }



}
