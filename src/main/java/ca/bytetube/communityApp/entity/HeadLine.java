package ca.bytetube.communityApp.entity;

import java.util.Date;

public class HeadLine {
    private Long lineId;

    private String lineName;

    // Headline link, click it will bring user to relative link.
    private String lineLink;

    // Headline image
    private String lineIng;

    private Integer priority;

    // 0. Disalbel 1.Enable
    private Integer enableStatus;

    private Date createTime;

    private Date lastEditTime;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getLineLink() {
        return lineLink;
    }

    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }

    public String getLineIng() {
        return lineIng;
    }

    public void setLineIng(String lineIng) {
        this.lineIng = lineIng;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
