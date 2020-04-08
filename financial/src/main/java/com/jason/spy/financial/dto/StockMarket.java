package com.jason.spy.financial.dto;


import lombok.Data;

@Data
public class StockMarket {
    private long statusID;
    private String region;
    private String status;
    private String timeZone;
}
