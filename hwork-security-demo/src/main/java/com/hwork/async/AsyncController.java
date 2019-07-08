package com.hwork.async;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/async")
public class AsyncController {
    Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping("/order")
    public DeferredResult<String> order() {
        logger.info("Main thread start...");

        String orderNumer = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumer);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getOrderResultMap().put(orderNumer,result);
        logger.info("Main thread end...");

        return result;
    }

}
