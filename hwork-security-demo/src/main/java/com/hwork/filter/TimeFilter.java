package com.hwork.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class TimeFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(TimeFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("TimeFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        long endTime = new Date().getTime();
        long timeSpent = endTime-startTime;
        logger.info("Time spent:"+timeSpent);
    }

    @Override
    public void destroy() {
        logger.info("TimeFilter destroy...");
    }
}
