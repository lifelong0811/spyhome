package com.jason.spy.financial.service.impl;

import com.jason.spy.financial.constant.BaseResponseEnum;
import com.jason.spy.financial.dto.BaseResponse;
import com.jason.spy.financial.dto.StockIndexResponse;
import com.jason.spy.financial.service.StockIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangleijie
 */
@Service
public class StockIndexServiceImpl implements StockIndexService {
    public static final String STOCK_INDEX_URL = "https://stock.xueqiu.com/v5/stock/quote.json?symbol=%s&extend=detail";
    public static final Logger LOGGER = LoggerFactory.getLogger(StockIndexServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public StockIndexResponse getStockIndexById(String id) {

        ResponseEntity<StockIndexResponse> response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cookie", "xq_a_token=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xqat=2ee68b782d6ac072e2a24d81406dd950aacaebe3; xq_r_token=f9a2c4e43ce1340d624c8b28e3634941c48f1052; xq_id_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1aWQiOi0xLCJpc3MiOiJ1YyIsImV4cCI6MTU4NzUyMjY2MSwiY3RtIjoxNTg2MzkzMTk0NjQ5LCJjaWQiOiJkOWQwbjRBWnVwIn0.kPNWKmI-d7g7Epmu5a_CBsYEamhjYJT722g7CKJD-b8jjZ_MvAYiriGfzhSVQFK7jO_wVN1_9zfH_l2ixolA_WMJ569z__rv_du8-vLRXKLBlsTNVDkctswGlU2cKjix94dwvqW9VXJL1-lohZaqDJ76711jZvgG9xZoipWpV-K77I2lOZ-tD5rZaiiN78vbrZCtr2gCaRAGqdVsIcAAbsiDNMSBd_kqmrMA8OSAf4272siwJqxr0O1uoSn5ZceoqJSi2bNOvl69njPV23_KAnAsLzQqkCO4zXCE9tD76qmgtOAP32ehIupVyXNJdLYuEp4lIJUcdWCxmbVzdDk2nw; u=731586393244170; Hm_lvt_1db88642e346389874251b5a1eded6e3=1584180291,1584180409,1586233639,1586273676; device_id=9e85991855915132572e237bffdb1601; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1586394292");
            response = restTemplate.exchange(String.format(STOCK_INDEX_URL, id), HttpMethod.GET, new HttpEntity<>(null, headers), StockIndexResponse.class);
        } catch (Exception e) {
            LOGGER.error("getStockIndexById exception, id: {}", id, e);
            return StockIndexResponse.buildFailure(BaseResponseEnum.SYSTEM_EXCEPTION, "getStockIndexById抛出异常", e.getMessage());
        }
        if (response == null) {
            return StockIndexResponse.buildFailure(BaseResponseEnum.OUTTER_ERROR, "getStockIndexById http请求返回为空", "id为: " + id);
        }
        if (response.getStatusCode() != HttpStatus.OK || response.getBody().getErrorCode() != 0) {
            return StockIndexResponse.buildFailure(BaseResponseEnum.OUTTER_ERROR, String.valueOf(response.getBody().getErrorCode()), response.getBody().getErrorDescription());
        }
        StockIndexResponse stockIndexResponse = response.getBody();
        stockIndexResponse.setBaseResponse(BaseResponse.buidSuccess());
        return stockIndexResponse;
    }
}
