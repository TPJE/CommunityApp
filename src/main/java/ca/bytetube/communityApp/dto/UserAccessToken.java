package ca.bytetube.communityApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccessToken {

    // Obtain credential
    @JsonProperty("access_token")
    private String accessToken;

    // Credential expire time (unit: second)
    @JsonProperty("expires_in")
    private String expiresIn;

    // Token refresh, to obtain the token next time. Not really useful here.
    @JsonProperty("refresh_token")
    private String refreshToken;

    // User identity on the platform, it is unique.
    @JsonProperty("openid")
    private String openId;

    // Access level, can skip here
    @JsonProperty("scope")
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "accessToken: " + this.getAccessToken() + ", openId: " + this.getOpenId();
    }
}
