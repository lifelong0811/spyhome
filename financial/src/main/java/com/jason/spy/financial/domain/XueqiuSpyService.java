package com.jason.spy.financial.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jason.spy.financial.constant.BaseResponseEnum;
import com.jason.spy.financial.dto.PushDTO;
import com.jason.spy.financial.dto.StockIndexResponse;
import com.jason.spy.financial.service.StockIndexService;
import com.jason.spy.financial.service.impl.ServerJiangMessagePush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangleijie
 */
@Service
public class XueqiuSpyService {
    @Autowired
    private ServerJiangMessagePush serverJiangMessagePush;

    @Autowired
    private StockIndexService stockIndexService;

    public void xueqiuSpy() {
        PushDTO pushDTO = new PushDTO();
        //上证指数(SH:000001)
        StockIndexResponse response = stockIndexService.getStockIndexById("SH000001");
        if (response.getErrorCode() != BaseResponseEnum.SUCCESS.getCode()) {
            pushDTO.setTitle("获取stock index错误");
            pushDTO.setDesp(JSON.toJSONString(response));
        } else {
            pushDTO.setTitle("上证指数盘口信息");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("> 当前点数: ").append(response.getData().getQuote().getHigh().toPlainString()).append("\n\n\n");
            stringBuilder.append("> 涨跌幅度: ").append(response.getData().getQuote().getPercent().toPlainString()).append("\n\n\n");
            pushDTO.setDesp(stringBuilder.toString());
        }
        serverJiangMessagePush.pushMessage(pushDTO);

    }
}
