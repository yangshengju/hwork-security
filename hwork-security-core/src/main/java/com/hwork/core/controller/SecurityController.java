package com.hwork.core.controller;

import com.hwork.core.support.SimpleResponse;
import com.hwork.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangs
 */
@RestController
public class SecurityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要认证时跳转到这里
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/authentication/required")
    @ResponseStatus(code= HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null) {
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的URL:"+targetUrl);
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")) {
                try {
                    redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new SimpleResponse("访问的服务需要认证，请引导用户到登录页！");
    }

    @GetMapping("/session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid() {
        String message = "session已经失效，请重新认证！";
        return new SimpleResponse(message);
    }
}
