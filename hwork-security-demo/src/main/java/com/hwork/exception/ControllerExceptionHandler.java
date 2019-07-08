package com.hwork.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by yangshengju on 2019-7-5.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(UserNotExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exceptionHandle(UserNotExistException ex) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("userId",ex.getUserId());
        resultMap.put("exMsg",ex.getLocalizedMessage());
        logger.info("leave exceptionHandle...");
        return resultMap;
    }
}
