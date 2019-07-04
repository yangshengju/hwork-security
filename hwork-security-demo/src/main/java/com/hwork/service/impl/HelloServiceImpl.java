package com.hwork.service.impl;

import com.hwork.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String greeting(String userName) {
        logger.info("greeting");
        return "hello "+userName;
    }
}
