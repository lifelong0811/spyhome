package com.jason.spy.financial.service.impl;

import com.google.common.collect.Maps;
import com.jason.spy.financial.constant.BaseResponseEnum;
import com.jason.spy.financial.dto.BaseResponse;
import com.jason.spy.financial.dto.PushDTO;
import com.jason.spy.financial.dto.ServerJiangResponse;
import com.jason.spy.financial.service.MessagePushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Map;

/**
 * @author wangleijie
 */
@Service
public class ServerJiangMessagePush implements MessagePushService {
    public static final String URL = "https://sc.ftqq.com/SCU92682Ta981f9ddf3ea3c46b9aa5f9e4a8e72dc5e89e2b568764.send?text=%s&desp=%s";
//    public static final String URL = "https://sc.ftqq.com/SCU92682Ta981f9ddf3ea3c46b9aa5f9e4a8e72dc5e89e2b568764.send";
    public static final Logger LOGGER = LoggerFactory.getLogger(ServerJiangMessagePush.class);
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseResponse pushMessage(PushDTO pushDTO) {
        ServerJiangResponse serverJiangResponse = null;
        try {
            Map<String, String> param = Maps.newHashMap();
            param.put("text", pushDTO.getTitle());
            param.put("desp", pushDTO.getDesp());
            serverJiangResponse = restTemplate.getForObject(String.format(URL, pushDTO.getTitle(), pushDTO.getDesp()), ServerJiangResponse.class);
//            serverJiangResponse = restTemplate.getForObject(URL, ServerJiangResponse.class, param);
        } catch (Exception e) {
            LOGGER.error("ServerJiangMessagePush.pushMessage exception, pushDTO: {} ", pushDTO, e);
            return BaseResponse.buidFailure(BaseResponseEnum.SYSTEM_EXCEPTION, "ServerJiangMessagePush.pushMessage exception", e.getMessage());
        }
        if (serverJiangResponse.getErrno() != 0) {
            return BaseResponse.buidFailure(BaseResponseEnum.OUTTER_ERROR, String.valueOf(serverJiangResponse.getErrno()), serverJiangResponse.getErrmsg());
        }
        return BaseResponse.buidSuccess();
    }
}
