package com.jason.spy.financial.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StockQuote {
    private String symbol;
    private long flatCount;
    private String code;
    private double high52W;
    private double avgPrice;
    private long delayed;
    private long type;
    private BigDecimal percent;
    private double tickSize;
    private long floatShares;
    private double amplitude;
    private double current;
    private BigDecimal high;
    private double currentYearPercent;
    private double floatMarketCapital;
    private long issueDate;
    private double low;
    private String subType;
    private long marketCapital;
    private String currency;
    private long lotSize;
    private long lockSet;
    private long timestamp;
    private long fallCount;
    private long riseCount;
    private double amount;
    private double chg;
    private double lastClose;
    private long volume;
    private double volumeRatio;
    private double turnoverRate;
    private double low52W;
    private String name;
    private String exchange;
    private long time;
    private long totalShares;
    private double open;
    private long status;
}
