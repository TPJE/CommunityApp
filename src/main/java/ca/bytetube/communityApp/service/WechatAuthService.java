package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.WechatAuthExecution;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.entity.WechatAuth;
import ca.bytetube.communityApp.enums.WechatAuthStateEnum;
import ca.bytetube.communityApp.exceptions.WechatAuthOperationException;

import java.util.Date;

public interface WechatAuthService {
    /**
     * Find related platform account binding to Wechat account by openId
     */

    WechatAuth getWechatAuthByOpenId(String openId);

    /**
     * Register the platform account for Wechat account
     */
    WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException;
}
