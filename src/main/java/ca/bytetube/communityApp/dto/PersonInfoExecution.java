package ca.bytetube.communityApp.dto;

import ca.bytetube.communityApp.entity.PersonInfo;
import ca.bytetube.communityApp.enums.PersonInfoStateEnum;

import java.util.List;

/**
 * Execution after encapsulate the result
 */
public class PersonInfoExecution {
    // Result state
    private int state;

    // State info
    private String stateInfo;

    // Shop count
    private int count;

    // PersonInfo for operation (POST, PUT, DELETE shop)
    private PersonInfo personInfo;

    // Obtain personInfo list (QUERY shop list)
    private List<PersonInfo> personInfoList;

    public PersonInfoExecution() {}

    // Constructor for Failure
    public PersonInfoExecution(PersonInfoStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // Constructor for Success
    public PersonInfoExecution(PersonInfoStateEnum stateEnum, PersonInfo personInfo) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.personInfo = personInfo;
    }

    // Constructor for Success
    public PersonInfoExecution(PersonInfoStateEnum stateEnum, List<PersonInfo> personInfoList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.personInfoList = personInfoList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public List<PersonInfo> getPersonInfoList() {
        return personInfoList;
    }

    public void setPersonInfoList(List<PersonInfo> personInfoList) {
        this.personInfoList = personInfoList;
    }
}
