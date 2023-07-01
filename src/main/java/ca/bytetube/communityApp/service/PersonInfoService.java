package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.dto.PersonInfoExecution;
import ca.bytetube.communityApp.entity.PersonInfo;

public interface PersonInfoService {
    /**
     * Get PersonInfo by userId
     * @param userId
     * @return PersonInfo
     */
    PersonInfo getPersonInfoById(Long userId);

    /**
     * Get PersonInfo list by condition
     * @param personInfoCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition, int pageIndex, int pageSize);

    /**
     * Update PersonInfo by input PersonInfo
     * @param personInfo
     * @return
     */
    PersonInfoExecution modifyPersonInfo(PersonInfo personInfo);
}
