package com.hwork.core.verificationcode;

import com.hwork.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangs
 */
@Component
public class VerificationCodeValidateFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urlsToProcess = new HashSet<>();

    public VerificationCodeValidateFilter(SecurityProperties securityProperties, AuthenticationFailureHandler authenticationFailureHandler) {
        this.securityProperties = securityProperties;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configProcessUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrls(),",");
        for(String url : configProcessUrls) {
            urlsToProcess.add(url);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        for(String urlPatternToProcess : urlsToProcess) {
            if (antPathMatcher.match(urlPatternToProcess,request.getRequestURI())) {
                try {
                    validate(new ServletWebRequest(request));
                } catch (ValidateCodeException e) {
                    authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                    return;
                }
                break;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) {
        String codeInSession = (String) sessionStrategy.getAttribute(request,VerificationCodeController.SESSION_KEY);
        LocalDateTime codeExpiredTimeInSession = (LocalDateTime) sessionStrategy.getAttribute(request,VerificationCodeController.SESSION_KEY_EXPIRED_TIME);
        String codeInRequest = request.getRequest().getParameter("imageCode");
        if(StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("输入的验证码不能为空！");
        }
        if(codeInSession==null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if(LocalDateTime.now().isAfter(codeExpiredTimeInSession)) {
            throw new ValidateCodeException("验证码已过期！");
        }
        if(!codeInRequest.equalsIgnoreCase(codeInSession)) {
            throw new ValidateCodeException("输入的验证码不正确！");
        }
        sessionStrategy.removeAttribute(request,VerificationCodeController.SESSION_KEY);
    }
}
