package com.aib.mobile.banking.authmanager.model;

public class Auth {

    private String accessToken;

    private String refreshToken;

    private String tokenType;


    public Auth(String accessToken, String refreshToken, String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
