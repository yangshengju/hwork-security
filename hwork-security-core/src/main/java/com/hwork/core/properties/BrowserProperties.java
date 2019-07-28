package com.hwork.core.properties;

public class BrowserProperties {
    private String loginPage = "/hwork-signIn.html";

    private String notAuthList = "";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getNotAuthList() {
        return notAuthList;
    }

    public void setNotAuthList(String notAuthList) {
        this.notAuthList = notAuthList;
    }
}
