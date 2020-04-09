package com.jason.spy.financial.service;

import com.jason.spy.financial.dto.BaseResponse;
import com.jason.spy.financial.dto.PushDTO;

public interface MessagePushService {
    BaseResponse pushMessage(PushDTO pushDTO);
}
