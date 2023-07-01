package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.LocalAuthExecution;
import ca.bytetube.communityApp.entity.LocalAuth;
import ca.bytetube.communityApp.exceptions.LocalAuthOperationException;
import org.apache.tomcat.jni.Local;

public interface LocalAuthService {
    /**
     * Obtain platform account info by username and password from entity
     */

    LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

    /**
     *
     * Obtain platform account info by userId
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * Associate Wechat and generate unique platform account
     */
    LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

    /**
     * Update platform account password
     */
    LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword)
        throws LocalAuthOperationException;
}
