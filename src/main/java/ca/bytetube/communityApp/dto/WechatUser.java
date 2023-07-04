package ca.bytetube.communityApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class WechatUser implements Serializable {
    private static final long serialVersionUID = -4684067645282292327L;

    // Unique id in the platform
    @JsonProperty("openid")
    private String openId;

    @JsonProperty("nichname")
    private String nickName;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("province")
    private String province;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    // Avatar url
    @JsonProperty("headimgurl")
    private String headImgUrl;

    // User access level, not really useful here
    @JsonProperty("privilege")
    private String[] privilege;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }
}
