package com.hwork.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    Logger logger = LoggerFactory.getLogger(TimeAspect.class);
    @Around("execution(* com.hwork.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
        logger.info(pjp.getSignature().getName() + "start");
        long startTime = new Date().getTime();
        Object result= null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = new Date().getTime();
        logger.info("Execute "+pjp.getSignature().getName()+" spent "+(endTime-startTime));
        logger.info(pjp.getSignature().getName()+" ended..");
        return result;
    }
}
