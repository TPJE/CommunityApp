package ca.bytetube.communityApp.service.impl;

import ca.bytetube.communityApp.dao.PersonInfoDao;
import ca.bytetube.communityApp.dto.PersonInfoExecution;
import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.enums.PersonInfoStateEnum;
import ca.bytetube.communityApp.exceptions.PersonInfoOperationException;
import ca.bytetube.communityApp.service.PersonInfoService;
import ca.bytetube.communityApp.util.PageCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonInfoServiceImpl implements PersonInfoService {
    private static Logger logger = LoggerFactory.getLogger(PersonInfoServiceImpl.class);
    @Autowired
    PersonInfoDao personInfoDao;

    @Override
    public PersonInfo getPersonInfoById(Long userId) {
        return personInfoDao.queryPersonInfoById(userId);
    }

    @Override
    public PersonInfoExecution getPersonInfoList(PersonInfo personInfoCondition, int pageIndex, int pageSize) {
        // Convert page to row
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);

        // Get person info list
        List<PersonInfo> personInfoList = personInfoDao.queryPersonInfoList(personInfoCondition, rowIndex, pageSize);

        int count = personInfoDao.queryPersonInfoCount(personInfoCondition);

        PersonInfoExecution se = new PersonInfoExecution();
        if(personInfoList != null) {
            se.setPersonInfoList(personInfoList);
            se.setCount(count);
        }
        else se.setState(PersonInfoStateEnum.INNER_ERROR.getState());

        return se;
    }

    @Override
    public PersonInfoExecution modifyPersonInfo(PersonInfo personInfo) {
        //Check if null
        if(personInfo == null || personInfo.getUserId() == null) return new PersonInfoExecution(PersonInfoStateEnum.EMPTY);

        try {
            // Update person info
            int effectedNum = personInfoDao.updatePersonInfo(personInfo);
            if(effectedNum <= 0) return new PersonInfoExecution(PersonInfoStateEnum.INNER_ERROR);

            personInfo = personInfoDao.queryPersonInfoById(personInfo.getUserId());
            return new PersonInfoExecution(PersonInfoStateEnum.SUCCESS, personInfo);
        } catch (Exception e) {
            logger.error("Update PersonInfo Error: " + e.getMessage());
            throw new PersonInfoOperationException("Update PersonInfo Error: " + e.getMessage());
        }
    }
}
