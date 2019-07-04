package com.hwork.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle:");
        long startTime = new Date().getTime();
        request.setAttribute("startTime",startTime);
        return true;
    }

    @Override
    public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("postHandle:");
        logTimeSpent(request,response,handler);

    }

    private void logTimeSpent(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = new Date().getTime();
        HandlerMethod method = (HandlerMethod)handler;
        String beanName=method.getBean().getClass().getName();
        String methodSignature = method.getMethod().getName();
        logger.info("Execute "+beanName+"'s "+methodSignature+" spent "+(endTime-startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("afterCompletion:");
        logTimeSpent(request,response,handler);
    }
}
