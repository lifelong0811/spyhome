package com.jason.spy.financial.service;

import com.jason.spy.financial.dto.StockIndexResponse;
import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockIndexServiceImplTest {

    @Autowired
    private StockIndexService stockIndexService;

    @Test
    @Ignore
    void getStockIndexById() {
        StockIndexResponse response = stockIndexService.getStockIndexById("SH000001");
        Assert.assertTrue("返回code错误", response.getErrorCode() == 0);
    }
}