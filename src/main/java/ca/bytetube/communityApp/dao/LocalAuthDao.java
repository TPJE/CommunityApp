package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface LocalAuthDao {
    /**
     * Login by checking username and password
     */
    LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

    /**
     * Check matched LocalAuth by user ID;
     */
    LocalAuth queryLocalByUserId(@Param("userId") long userId);

    /**
     * Add platform account
     */
    int insertLocalAuth(LocalAuth localAuth);

    /**
     * Change password by userId, username, and password
     */
    int updateLocalAuth(@Param("userId") long userId,
                        @Param("username") String username,
                        @Param("password") String password,
                        @Param("newPassword") String newPassword,
                        @Param("lastEditTime") Date lastEditTime);
}
