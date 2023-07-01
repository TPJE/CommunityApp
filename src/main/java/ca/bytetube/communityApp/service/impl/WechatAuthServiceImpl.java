package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.PersonInfoDao;
import ca.bytetube.communityApp.dao.WechatAuthDao;
import ca.bytetube.communityApp.dto.WechatAuthExecution;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.entity.WechatAuth;
import ca.bytetube.communityApp.enums.WechatAuthStateEnum;
import ca.bytetube.communityApp.exceptions.WechatAuthOperationException;
import ca.bytetube.communityApp.service.WechatAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class WechatAuthServiceImpl implements WechatAuthService {

    private static Logger logger = LoggerFactory.getLogger(WechatAuthServiceImpl.class);

    @Autowired
    private WechatAuthDao wechatAuthDao;
    @Autowired
    private PersonInfoDao personInfoDao;

    @Override
    public WechatAuth getWechatAuthByOpenId(String openId) {
        return wechatAuthDao.queryWechatInfoByOpenId(openId);
    }

    @Override
    public WechatAuthExecution register(WechatAuth wechatAuth) throws WechatAuthOperationException {
        // Check if null
        if(wechatAuth == null || wechatAuth.getOpenId() == null) return new WechatAuthExecution(WechatAuthStateEnum.NULL_AUTH_INFO);

        try {
            // Set create time
            wechatAuth.setCreateTime(new Date());

            // Check if openId is null from Wechat account person info.
            // if null => new user login via Wechat account, then create new PersonInfo
            if(wechatAuth.getPersonInfo() != null && wechatAuth.getPersonInfo().getUserId() == null) {
                try {
                    wechatAuth.getPersonInfo().setCreateTime(new Date());
                    wechatAuth.getPersonInfo().setEnableStatus(1);
                    PersonInfo personInfo = wechatAuth.getPersonInfo();
                    int effectNum = personInfoDao.insertPersonInfo(personInfo);
                    wechatAuth.setPersonInfo(personInfo);
                    if(effectNum <= 0) {
                        throw new WechatAuthOperationException("Failed to add user information");
                    }
                } catch (Exception e) {
                    logger.error("Insert PersonInfo error: " + e.getMessage());
                    throw new WechatAuthOperationException("Insert PersonInfo error: " + e.getMessage());
                }
            }

            // Create unique wechat account for our platform
            int effectedNum = wechatAuthDao.insertWechatAuth(wechatAuth);

            if(effectedNum <= 0) throw new WechatAuthOperationException("Account create failed");
            else return new WechatAuthExecution(WechatAuthStateEnum.SUCCESS, wechatAuth);

        } catch (Exception e) {
            logger.error("Insert WechatAuth Error: " + e.getMessage());
            throw new WechatAuthOperationException("Insert WechatAuth Error: " + e.getMessage());
        }
    }
}
