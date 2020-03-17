package com.hwork.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage = "/hwork-signIn.html";

    private String notAuthList = "";

    private LoginType loginType = LoginType.JSON;
}
