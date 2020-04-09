package com.jason.spy.financial.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class XueqiuSpyServiceTest {
    @Autowired
    private XueqiuSpyService xueqiuSpyService;

    @Test
    void xueqiuSpy() {
        xueqiuSpyService.xueqiuSpy();
    }
}