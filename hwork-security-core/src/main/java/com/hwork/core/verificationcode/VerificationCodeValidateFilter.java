package com.hwork.core.verificationcode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationCodeValidateFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/authentication/form".equalsIgnoreCase(request.getRequestURI()) &&  "post".equalsIgnoreCase(request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            }catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
            filterChain.doFilter(request,response);
    }

    private void validate(ServletWebRequest request) {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,VerificationCodeController.SESSION_KEY);
        String codeInRequest = request.getRequest().getParameter("imageCode");
        if(StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("输入的验证码不能为空！");
        }
        if(codeInSession==null) {
            throw new ValidateCodeException("验证码不存在！");
        }
        if(codeInSession.isExpired()) {
            throw new ValidateCodeException("验证码已过期！");
        }
        if(!codeInRequest.equalsIgnoreCase(codeInSession.getCode())) {
            throw new ValidateCodeException("输入的验证码不正确！");
        }
        sessionStrategy.removeAttribute(request,VerificationCodeController.SESSION_KEY);
    }
}
