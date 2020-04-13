package com.jason.spy.financial.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jason.spy.financial.constant.BaseResponseEnum;
import com.jason.spy.financial.dto.PushDTO;
import com.jason.spy.financial.dto.StockIndexResponse;
import com.jason.spy.financial.dto.StockMarket;
import com.jason.spy.financial.dto.StockQuote;
import com.jason.spy.financial.service.StockIndexService;
import com.jason.spy.financial.service.impl.ServerJiangMessagePush;
import com.jason.spy.financial.tool.MarkdownTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangleijie
 */
@Service
public class XueqiuSpyService {

    public static final Map<String, String> stockIndexMap = Maps.newHashMap();

    static {
        stockIndexMap.put("SH000001", "上证指数");
        stockIndexMap.put("SH000300", "沪深300");
        stockIndexMap.put(".DJI", "道琼斯指数");
        stockIndexMap.put(".IXIC", "纳斯达克");
    }
    @Autowired
    private ServerJiangMessagePush serverJiangMessagePush;

    @Autowired
    private StockIndexService stockIndexService;

    public void xueqiuSpy() {
        PushDTO pushDTO = new PushDTO();
        pushDTO.setTitle("雪球财经大盘监控");
        pushDTO.setDesp(getStockIndex().toString());
        serverJiangMessagePush.pushMessage(pushDTO);
    }

    private MarkdownTool getStockIndex() {
        MarkdownTool tool = new MarkdownTool();
        for (String index : stockIndexMap.keySet()) {
            tool.header(stockIndexMap.get(index));
            StockIndexResponse response = stockIndexService.getStockIndexById(index);
            if (response.getErrorCode() != BaseResponseEnum.SUCCESS.getCode()) {
                tool.quote("获取指数信息失败，错误码: " + response.getErrorDescription());
            } else {
                StockQuote quote = response.getData().getQuote();
                StockMarket market = response.getData().getMarket();
                tool.quote("当前状态: " + market.getStatus());
                tool.quote("当前点数: " + quote.getCurrent().toPlainString());
                tool.quote("涨跌幅度: " + quote.getPercent().toPlainString());
            }
        }
        return tool;
    }
}
