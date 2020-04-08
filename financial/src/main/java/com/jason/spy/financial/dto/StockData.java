package com.jason.spy.financial.dto;

import lombok.Data;

@Data
public class StockData {
    private StockMarket market;
    private StockQuote quote;
    private String[] tags;
}
