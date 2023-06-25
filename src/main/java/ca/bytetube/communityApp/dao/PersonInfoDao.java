package ca.bytetube.communityApp.dao;

import ca.bytetube.communityApp.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {
    /**
     * Return PersonInfo list by research condition
     */
    List<PersonInfo> queryPersonInfoList(@Param("personInfoCondition") PersonInfo personInfoCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * Return the total according to the query conditions, and use it with queryPersonInfoList
     */
    int queryPersonInfoCount(@Param("personInfoCondition") PersonInfo personInfoCondition);

    /**
     * Search user by user ID
     */
    PersonInfo queryPersonInfoById(long userId);

    /**
     * Insert PersonInfo
     */
    int insertPersonInfo(PersonInfo personInfo);

    /**
     * Update PersonInfo
     */
    int updatePersonInfo(PersonInfo personInfo);

}
