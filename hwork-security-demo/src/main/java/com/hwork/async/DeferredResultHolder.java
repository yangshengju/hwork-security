package com.hwork.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeferredResultHolder {
    private Map<String, DeferredResult<String>> orderResultMap = new HashMap<>();

    public Map<String, DeferredResult<String>> getOrderResultMap() {
        return orderResultMap;
    }

    public void setOrderResultMap(Map<String, DeferredResult<String>> orderResultMap) {
        this.orderResultMap = orderResultMap;
    }
}
