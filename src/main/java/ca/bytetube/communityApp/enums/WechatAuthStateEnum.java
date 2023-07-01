package ca.bytetube.communityApp.enums;

import ca.bytetube.communityApp.service.impl.WechatAuthServiceImpl;

public enum WechatAuthStateEnum {
    LOGINFAIL(-1, "OpenId Error"),
    SUCCESS(0, "Created Successfully"),
    NULL_AUTH_INFO(-1006, "Null Auth Info");

    private int state;

    private String stateInfo;

    private WechatAuthStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static WechatAuthStateEnum stateOf(int index) {
        for(WechatAuthStateEnum state : values()) {
            if(state.getState() == index)  return state;
        }
            return null;
    }
}
