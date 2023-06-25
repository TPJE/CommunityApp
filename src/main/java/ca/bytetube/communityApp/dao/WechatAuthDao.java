package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.WechatAuth;

public interface WechatAuthDao {
    /**
     * Find the Wechat account which associated to platform
     */
    WechatAuth queryWechatInfoByOpenId(String openId);

    /**
     * Insert associated Wechat account
     */
    int insertWechatAuth(WechatAuth wechatAuth);
}
