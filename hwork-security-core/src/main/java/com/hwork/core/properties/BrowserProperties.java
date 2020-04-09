package com.hwork.core.properties;

import lombok.Data;

/**
 * @author yangs
 */
@Data
public class BrowserProperties {
    private String loginPage = "/hwork-signIn.html";

    private String notAuthList = "";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;
}
