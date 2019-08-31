package com.hwork.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class HworkSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public HworkSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /**
     *
     * @param object (SocialAuthenticationFilter)
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {

        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) socialAuthenticationFilter;
    }
}
