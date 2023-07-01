package ca.bytetube.communityApp.service;

import ca.bytetube.communityApp.entity.HeadLine;

import java.util.List;

public interface HeadLineService {
    public final String HLLISTKEY = "headlinelist";
    /**
     * Return specify headline list by condition
     */
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
