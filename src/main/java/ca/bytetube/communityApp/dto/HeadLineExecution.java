package ca.bytetube.communityApp.dto;

import ca.bytetube.communityApp.entity.HeadLine;
import ca.bytetube.communityApp.enums.HeadLineStateEnum;

import java.util.List;

public class HeadLineExecution {
    // Result state
    private int state;

    // State info
    private String stateInfo;

    // Total shop count
    private int count;

    // Operation headLine (for INSERT, UPDATE, DELETE product)
    private HeadLine headLine;

    // Get headLine list (for GET product list)
    private List<HeadLine> headLineList;

    public HeadLineExecution(){};

    // Constructor for Failed
    public HeadLineExecution(HeadLineStateEnum stateEnum, HeadLine headLine) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    // Constructor for Success
    public HeadLineExecution(HeadLineStateEnum stateEnum, List<HeadLine> headLineList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.headLineList = headLineList;
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

    public HeadLine getHeadLine() {
        return headLine;
    }

    public void setHeadLine(HeadLine headLine) {
        this.headLine = headLine;
    }

    public List<HeadLine> getHeadLineList() {
        return headLineList;
    }

    public void setHeadLineList(List<HeadLine> headLineList) {
        this.headLineList = headLineList;
    }
}
