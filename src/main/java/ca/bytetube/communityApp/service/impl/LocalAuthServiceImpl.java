package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.LocalAuthDao;
import ca.bytetube.communityApp.dto.LocalAuthExecution;
import ca.bytetube.communityApp.entity.LocalAuth;
import ca.bytetube.communityApp.enums.LocalAuthStateEnum;
import ca.bytetube.communityApp.exceptions.LocalAuthOperationException;
import ca.bytetube.communityApp.service.LocalAuthService;
import ca.bytetube.communityApp.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class LocalAuthServiceImpl implements LocalAuthService {
    @Autowired
    private LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(username, MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }

    @Override
    @Transactional
    public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
        // Check if input value is null (username, password, and userId),
        // throw exception if any null exist.
        if (localAuth == null ||
                localAuth.getPassword() == null ||
                localAuth.getUsername() == null ||
                localAuth.getPersonInfo() == null ||
                localAuth.getPersonInfo().getUserId() == null) {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }

        try {
            // Create and bind a new platform account to the user if never did before
            localAuth.setCreateTime(new Date());
            localAuth.setLastEditTime(new Date());
            // Encrypt password by MD5
            localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
            int effectedNum = localAuthDao.insertLocalAuth(localAuth);

            // Check if success
            if (effectedNum <= 0) {
                throw new LocalAuthOperationException("Account binding failed");
            } else {
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
            }
        } catch (Exception e) {
            throw new LocalAuthOperationException("Insert LocalAuth Error: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
        // Check if any null value exist (userId, username, password, newPassword), if password and newPassword are the same.
        // Throw exception if condition not met.
        if (userId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)) {
            try {
                // Update password and encrypt by MD5
                int effectedNum = localAuthDao.updateLocalAuth(userId, username, MD5.getMd5(password), MD5.getMd5(newPassword), new Date());

                // Check if success
                if (effectedNum <= 0) {
                    throw new LocalAuthOperationException("Failed to update password");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new LocalAuthOperationException("Failed to update password: " + e.getMessage());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }
}
