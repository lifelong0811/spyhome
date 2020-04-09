package com.jason.spy.financial.service;

import com.alibaba.fastjson.JSON;
import com.jason.spy.financial.dto.BaseResponse;
import com.jason.spy.financial.dto.PushDTO;
import com.jason.spy.financial.service.impl.ServerJiangMessagePush;
import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerJiangMessagePushTest {

    @Autowired
    private ServerJiangMessagePush messagePush;
//    @Test
    void pushMessage() {
        PushDTO pushDTO = new PushDTO();
        pushDTO.setTitle("hello" + System.currentTimeMillis());
        pushDTO.setDesp(JSON.toJSONString(pushDTO));
        BaseResponse response = messagePush.pushMessage(pushDTO);
        Assert.assertTrue("推送消息失败" + JSON.toJSONString(response), response.getCode() == 0);
    }
}